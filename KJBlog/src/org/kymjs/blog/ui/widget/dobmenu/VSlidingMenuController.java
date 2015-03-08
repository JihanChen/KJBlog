package org.kymjs.blog.ui.widget.dobmenu;

import org.kymjs.blog.R;
import org.kymjs.blog.ui.widget.dobmenu.SlidingItem.SlidingType;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class VSlidingMenuController {

    public enum SlidingStatus {
        COLLAPSED, EXPANDED, ANIMATING
    }

    public static final float DEFAULT_JUMP_LINE_PERCENTAGE = 0.6f;
    public static final int DEFAULT_INT = -1;

    private final Activity activity;
    private SlidingItem slidingItem;

    private ViewGroup decor;
    public View actionBarView;
    private ViewGroup content;
    private ImageView handle;

    private FrameLayout slidingParent;
    private FrameLayout.LayoutParams slidingLayoutParams;
    protected int slidingHeight;

    private float jumpLine;

    private OnSizingTouchListener sizingTouchListener;
    private OnMovingTouchListener movingTouchListener;

    private AnimationExecutor animationExecutor;

    public VSlidingMenuController(Activity activity, SlidingItem slidingItem) {
        super();
        this.activity = activity;
        this.slidingItem = slidingItem;
        init();
    }

    private void init() {
        this.slidingParent = new FrameLayout(activity);

        animationExecutor = new AnimationExecutor(this);

        decor = (ViewGroup) activity.getWindow().getDecorView();

        content = (ViewGroup) decor.findViewById(android.R.id.content);
        content.addView(slidingParent);

        handle = initHandle(activity, this, slidingItem);
        handle.setOnTouchListener(movingTouchListener);
        actionBarView = decor.findViewById(R.id.titlebar);

        setSlidingType(slidingItem.getSlidingType());

        sizingTouchListener = new OnSizingTouchListener(this);
        movingTouchListener = new OnMovingTouchListener(this);
    }

    public void setSlidingView(View slidingView) {
        if (this.slidingParent.getChildCount() > 0) {
            this.slidingParent.removeViewAt(0);
        }

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        slidingView.setLayoutParams(params);

        this.slidingParent.addView(slidingView);

        prepareSlidingLayout();

        hideSlidingLayout();
    }

    protected void prepareSlidingLayout() {
        this.slidingLayoutParams = (FrameLayout.LayoutParams) slidingParent
                .getLayoutParams();

        ViewTreeObserver vto = slidingParent.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @SuppressLint("NewApi")
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                hideSlidingLayout();

                ViewTreeObserver obs = slidingParent.getViewTreeObserver();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    obs.removeOnGlobalLayoutListener(this);

                } else {
                    obs.removeGlobalOnLayoutListener(this);
                }
            }

        });

        SlidingParentTouchListener parentTouchListener = new SlidingParentTouchListener();
        parentTouchListener.register(this);
        slidingParent.setOnTouchListener(parentTouchListener);

        SlidingParentKeyListener parentKeyListener = new SlidingParentKeyListener(
                this);
        slidingParent.setOnKeyListener(parentKeyListener);
    }

    protected void hideSlidingLayout() {
        slidingHeight = content.getHeight();
        jumpLine = slidingHeight * slidingItem.getJumpLinePercentage();

        slidingLayoutParams.height = slidingHeight;
        slidingParent.setLayoutParams(slidingLayoutParams);

        if (slidingItem.getSlidingType() == SlidingType.SIZE) {
            slidingLayoutParams.height = 0;
            slidingLayoutParams.topMargin = 0;

        } else if (slidingItem.getSlidingType() == SlidingType.MOVE) {
            slidingLayoutParams.topMargin = -slidingHeight;
            slidingLayoutParams.height = slidingHeight;
        }
        slidingParent.setLayoutParams(slidingLayoutParams);
    }

    public void expand() {
        animateSliding(0, slidingHeight);

        focusOnSliding();
    }

    public void collapse() {
        animateSliding(slidingHeight, 0);
    }

    public void finish() {
        collapse();
    }

    public void focusOnSliding() {
        slidingParent.setFocusable(true);
        slidingParent.setFocusableInTouchMode(true);
        slidingParent.requestFocus();
    }

    public void animateSliding(int fromY, int toY) {
        if (slidingItem.isEnabled()) {
            animationExecutor.animateView(fromY, toY);
        }
    }

    public SlidingItem getSlidingItem() {
        return slidingItem;
    }

    public void setSlidingItem(SlidingItem slidingItem) {
        this.slidingItem = slidingItem;
    }

    public void setEnabled(boolean enabled) {
        hideSlidingLayout();

        if (enabled) {
            setUseHandle(slidingItem.isUseHandle());

        } else {
            setUseHandle(false);
        }
    }

    public SlidingStatus getSlidingStatus() {
        return getSlidingStatus(this);
    }

    public void setViewHeight(int viewHeight) {
        slidingLayoutParams.height = viewHeight;
        slidingParent.setLayoutParams(slidingLayoutParams);
    }

    public int getViewHeight() {
        return slidingLayoutParams.height;
    }

    public void setViewTop(int viewTop) {
        slidingLayoutParams.topMargin = viewTop - slidingHeight;
        slidingParent.setLayoutParams(slidingLayoutParams);
    }

    public int getViewTop() {
        return slidingLayoutParams.topMargin;
    }

    public void setSlidingType(SlidingType slidingType) {
        if (slidingType == SlidingType.SIZE) {
            actionBarView.setOnTouchListener(sizingTouchListener);

        } else if (slidingType == SlidingType.MOVE) {
            actionBarView.setOnTouchListener(movingTouchListener);
        }

        if (slidingItem.getSlidingView() != null) {
            hideSlidingLayout();
        }
    }

    public void setUseHandle(boolean useHandle) {
        ViewGroup actionBarParent = (ViewGroup) actionBarView.getParent();
        if (useHandle) {
            if (handle.getParent() != actionBarParent) {
                actionBarParent.addView(handle);
            }

        } else {
            if (handle.getParent() == actionBarParent) {
                actionBarParent.removeView(handle);
            }
        }
    }

    public FrameLayout getSlidingParent() {
        return slidingParent;
    }

    public int getSlidingHeight() {
        return slidingHeight;
    }

    public float getJumpLine() {
        return jumpLine;
    }

    public static final VSlidingMenuController.SlidingStatus getSlidingStatus(
            VSlidingMenuController vSlidingMenuController) {

        FrameLayout slidingParent = vSlidingMenuController.getSlidingParent();
        FrameLayout.LayoutParams slidingLayoutParams = (FrameLayout.LayoutParams) slidingParent
                .getLayoutParams();

        if (vSlidingMenuController.getSlidingItem().getSlidingType() == SlidingType.SIZE) {

            int currentSlidingHeight = slidingParent.getHeight();

            if (currentSlidingHeight == 0) {
                return VSlidingMenuController.SlidingStatus.COLLAPSED;

            } else if (currentSlidingHeight >= vSlidingMenuController
                    .getSlidingHeight()) {
                return VSlidingMenuController.SlidingStatus.EXPANDED;

            } else {
                return VSlidingMenuController.SlidingStatus.ANIMATING;
            }

        } else if (vSlidingMenuController.getSlidingItem().getSlidingType() == SlidingType.MOVE) {

            int currentSlidingTop = slidingLayoutParams.topMargin;

            if (currentSlidingTop <= -vSlidingMenuController.getSlidingHeight()) {
                return VSlidingMenuController.SlidingStatus.COLLAPSED;

            } else if (currentSlidingTop >= 0) {
                return VSlidingMenuController.SlidingStatus.EXPANDED;

            } else {
                return VSlidingMenuController.SlidingStatus.ANIMATING;
            }

        } else {
            return VSlidingMenuController.SlidingStatus.ANIMATING;
        }
    }

    public static final ImageView initHandle(Context context,
            VSlidingMenuController slidingMenuController,
            SlidingItem slidingItem) {
        ImageView handle = new ImageView(context);
        handle.setScaleType(ImageView.ScaleType.CENTER);
        handle.setContentDescription("");

        FrameLayout.LayoutParams handleLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM
                        | Gravity.CENTER_HORIZONTAL);
        handle.setLayoutParams(handleLayoutParams);
        return handle;
    }

}

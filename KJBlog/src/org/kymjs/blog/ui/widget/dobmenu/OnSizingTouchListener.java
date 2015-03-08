package org.kymjs.blog.ui.widget.dobmenu;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;

/**
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class OnSizingTouchListener implements OnTouchListener {

    private final CurtainViewController vSlidingMenuController;

    private FrameLayout slidingParent;
    private FrameLayout.LayoutParams slidingLayoutParams;

    public OnSizingTouchListener(CurtainViewController vSlidingMenuController) {
        super();
        this.vSlidingMenuController = vSlidingMenuController;

        init();
    }

    private void init() {
        this.slidingParent = this.vSlidingMenuController.getSlidingParent();
        this.slidingLayoutParams = (FrameLayout.LayoutParams) this.slidingParent
                .getLayoutParams();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (vSlidingMenuController.getSlidingItem().isEnabled()) {
            float y = event.getY();

            switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                vSlidingMenuController.focusOnSliding();

                if (slidingParent.getHeight() > 0) {
                    return false;
                }

                break;

            case MotionEvent.ACTION_MOVE:
                slidingLayoutParams.height = (int) y;
                slidingParent.setLayoutParams(slidingLayoutParams);
                break;

            case MotionEvent.ACTION_UP:
                if (y > vSlidingMenuController.getJumpLine()) {
                    vSlidingMenuController.animateSliding((int) y,
                            vSlidingMenuController.getSlidingHeight());

                } else {
                    vSlidingMenuController.animateSliding((int) y, 0);
                }

                break;

            }
        }
        return true;
    }

}

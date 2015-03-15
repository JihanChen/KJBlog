package org.kymjs.blog.ui;

import org.kymjs.blog.R;
import org.kymjs.blog.ui.widget.dobmenu.CurtainItem.OnSwitchListener;
import org.kymjs.blog.ui.widget.dobmenu.CurtainItem.SlidingType;
import org.kymjs.blog.ui.widget.dobmenu.CurtainView;
import org.kymjs.blog.utils.KJAnimations;
import org.kymjs.blog.utils.PullTip;
import org.kymjs.kjframe.KJActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 应用Activity基类
 * 
 * @author kymjs (https://github.com/kymjs)
 * @author lody
 * @since 2015-3
 * 
 *        FIX 2015/3/15 : 修复ActionBar点击不会调的问题。
 */
public abstract class TitleBarActivity extends KJActivity {

    public ImageView mImgBack;
    public TextView mTvTitle;
    public TextView mTvDoubleClickTip;
    public ImageView mImgMenu;

    protected final Handler mMainLoopHandler = new Handler(
            Looper.getMainLooper());

    // Sliding menu object
    private CurtainView mCurtainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        try {
            mImgBack = (ImageView) findViewById(R.id.titlebar_img_back);
            mTvTitle = (TextView) findViewById(R.id.titlebar_text_title);
            mTvDoubleClickTip = (TextView) findViewById(R.id.titlebar_text_exittip);
            mImgMenu = (ImageView) findViewById(R.id.titlebar_img_menu);
            mImgBack.setOnClickListener(this);
            mImgMenu.setOnClickListener(this);
            initCurtainView();
        } catch (NullPointerException e) {
            throw new NullPointerException(
                    "TitleBar Notfound from Activity layout");
        }
        super.onStart();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.titlebar_img_back:
            onBackClick();
            break;
        case R.id.titlebar_img_menu:
            onMenuClick();
            break;
        default:
            break;
        }
    }

    protected void onBackClick() {}

    protected void onMenuClick() {}

    public void onCurtainPull() {}

    public void onCurtainPush() {}

    /********************** 窗帘视图相关 *****************************/

    private static int count = 0;

    private final Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            count = 0;
        }
    };

    public CurtainView getCurtainView() {
        return mCurtainView;
    }

    private void initCurtainView() {
        mCurtainView = new CurtainView(this, R.id.titlebar);
        mCurtainView.setSlidingView(R.layout.dob_sliding_menu);
        ImageView mImgLockScreen = (ImageView) mCurtainView.getContentView()
                .findViewById(R.id.lockscreen);
        mImgLockScreen
                .setImageResource(PullTip.lockScreen[(int) (Math.random() * PullTip.lockScreen.length)]);
        mCurtainView.setMaxDuration(1000);
        mCurtainView.setSlidingType(SlidingType.MOVE);

        mCurtainView.setOnSwitchListener(new OnSwitchListener() {
            @Override
            public void onCollapsed() {
                onCurtainPush();
            }

            @Override
            public void onExpanded() {
                onCurtainPull();
                mMainLoopHandler.postDelayed(timerRunnable, 3000);
                count++;
                if (count > 2) {
                    mMainLoopHandler.removeCallbacks(timerRunnable);
                    Toast.makeText(
                            aty,
                            PullTip.toast[(int) (Math.random() * PullTip.toast.length)],
                            Toast.LENGTH_SHORT).show();
                    count = 0;
                } else if (count > 1) {
                    mMainLoopHandler.removeCallbacks(timerRunnable);
                    mMainLoopHandler.postDelayed(timerRunnable, 3000);
                }
            }
        });
        mCurtainView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                KJAnimations.clickCurtain(mCurtainView.getContentParentView());
                mMainLoopHandler.postDelayed(timerRunnable, 2000);
                count++;
                if (count > 2) {
                    mMainLoopHandler.removeCallbacks(timerRunnable);
                    Toast.makeText(
                            aty,
                            PullTip.toast[(int) (Math.random() * PullTip.toast.length)],
                            Toast.LENGTH_SHORT).show();
                    count = 0;
                } else if (count == 2) {
                    mMainLoopHandler.removeCallbacks(timerRunnable);
                    mMainLoopHandler.postDelayed(timerRunnable, 2000);
                }
            }
        });
    }
}

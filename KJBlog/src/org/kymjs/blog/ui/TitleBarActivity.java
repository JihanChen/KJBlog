package org.kymjs.blog.ui;

import org.kymjs.blog.AppContext;
import org.kymjs.blog.R;
import org.kymjs.blog.ui.widget.dobmenu.CurtainItem.OnSwitchListener;
import org.kymjs.blog.ui.widget.dobmenu.CurtainItem.SlidingType;
import org.kymjs.blog.ui.widget.dobmenu.CurtainView;
import org.kymjs.kjframe.KJActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 应用Activity基类
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public abstract class TitleBarActivity extends KJActivity {

    public ImageView mImgBack;
    public TextView mTvTitle;
    public ImageView mImgMenu;

    protected AppContext app;

    // Sliding menu object
    private CurtainView vSlidingMenu;

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
            mImgMenu = (ImageView) findViewById(R.id.titlebar_img_menu);
            mImgBack.setOnClickListener(this);
            mImgMenu.setOnClickListener(this);
            initDobSlidMenu();
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

    @Override
    public void initData() {
        super.initData();
        app = (AppContext) getApplication();
    }

    protected void onBackClick() {}

    protected void onMenuClick() {}

    private void initDobSlidMenu() {

        vSlidingMenu = new CurtainView(this, R.id.titlebar);
        vSlidingMenu.setSlidingType(SlidingType.SIZE);
        vSlidingMenu.setSlidingView(R.layout.dob_sliding_menu);
        vSlidingMenu.setMaxDuration(1000);
        View slidingView = vSlidingMenu.getSlidingView();
        slidingView.findViewById(R.id.clickMe).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vSlidingMenu.finish();
                    }
                });
        vSlidingMenu.setOnSwitchListener(new OnSwitchListener() {
            @Override
            public void onCollapsed() {
                Log.i("kymjs", "onCollapsed");
            }

            @Override
            public void onExpanded() {
                Log.i("kymjs", "onExpanded");
            }
        });
    }

    public void initViews() {
        vSlidingMenu.setSlidingType(SlidingType.MOVE);
        vSlidingMenu.expand();
    }
}

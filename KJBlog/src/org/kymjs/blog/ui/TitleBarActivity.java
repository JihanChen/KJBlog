package org.kymjs.blog.ui;

import org.kymjs.blog.R;
import org.kymjs.blog.ui.widget.dobmenu.CurtainItem.OnSwitchListener;
import org.kymjs.blog.ui.widget.dobmenu.CurtainItem.SlidingType;
import org.kymjs.blog.ui.widget.dobmenu.CurtainView;
import org.kymjs.kjframe.KJActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
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

    private void initCurtainView() {
        mCurtainView = new CurtainView(this, R.id.titlebar);
        mCurtainView.setSlidingView(R.layout.dob_sliding_menu);
        mCurtainView.setMaxDuration(1000);
        mCurtainView.setOnSwitchListener(new OnSwitchListener() {
            @Override
            public void onCollapsed() {}

            @Override
            public void onExpanded() {}
        });
    }

    public void initViews() {
        mCurtainView.setSlidingType(SlidingType.MOVE);
        mCurtainView.expand();
    }
}

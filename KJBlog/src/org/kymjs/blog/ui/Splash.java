package org.kymjs.blog.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import org.kymjs.blog.AppConfig;
import org.kymjs.blog.AppContext;
import org.kymjs.blog.R;
import org.kymjs.blog.utils.KJAnimations;
import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.FileUtils;
import org.kymjs.kjframe.utils.PreferenceHelper;
import org.kymjs.kjframe.utils.StringUtils;
import org.kymjs.kjframe.widget.RoundImageView;

/**
 * 应用欢迎界面(动态适配模式)
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class Splash extends KJActivity {
    public static final String TAG = "splash";

    @BindView(id = R.id.splash_layout_root)
    private RelativeLayout mRlRoot;
    @BindView(id = R.id.splash_box)
    private RelativeLayout mRlBox;
    @BindView(id = R.id.splash_tv_content)
    private TextView mTvContent;
    @BindView(id = R.id.splash_img_head)
    private RoundImageView mImgHead;
    @BindView(id = R.id.splash_btn_go, click = true)
    private Button mBtnGo;

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_splash);
    }

    @Override
    public void initData() {
        super.initData();
        String cacheTime = PreferenceHelper.readString(aty, TAG,
                AppConfig.CACHE_TIME_KEY, "");
        if (!StringUtils.getDataTime("yyyymmdd").equalsIgnoreCase(cacheTime)) {
            PreferenceHelper.clean(aty, TAG);
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        screenAdaptation();
        KJAnimations.openLoginAnim(mRlBox);
        mImgHead.setAnimation(KJAnimations.getRotateAnimation(360, 0, 100/**DEBUG MODE*/));
        setUserInterface();
    }

    /**
     * 屏幕适配
     */
    private void screenAdaptation() {
        RelativeLayout.LayoutParams boxParams = (LayoutParams) mRlBox
                .getLayoutParams();
        boxParams.width = (int) (AppContext.screenW * 0.8);
        boxParams.height = (int) (AppContext.screenH * 0.6);
        mRlBox.setLayoutParams(boxParams);

        RelativeLayout.LayoutParams goParams = (LayoutParams) mBtnGo
                .getLayoutParams();
        goParams.width = (int) (AppContext.screenW * 0.7);
        goParams.height = (int) getResources().getDimension(
                R.dimen.splash_btn_go_height);
        mBtnGo.setLayoutParams(goParams);

        RelativeLayout.LayoutParams headParams = (RelativeLayout.LayoutParams) mImgHead
                .getLayoutParams();
        headParams.topMargin = (int) ((AppContext.screenH * 0.16) / 2);
        mImgHead.setLayoutParams(headParams);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.splash_btn_go:
            startActivity(new Intent(aty, Main.class));
            finish();
            break;
        default:
            break;
        }
    }

    /**
     * 动态设置用户界面
     */
    private void setUserInterface() {
        String sdCardPath = FileUtils.getSavePath(AppConfig.SAVE_FOLDER);
        String headImgPath = sdCardPath
                + PreferenceHelper.readString(aty, TAG,
                        AppConfig.SPLASH_HEAD_IMG_KEY, "");
        String rootBgPath = sdCardPath
                + PreferenceHelper.readString(aty, TAG,
                        AppConfig.SPLASH_BACKGROUND_KEY, "");
        String boxBgPath = sdCardPath
                + PreferenceHelper.readString(aty, TAG,
                        AppConfig.SPLASH_BOX_KEY, "");
        String contentStr = PreferenceHelper.readString(aty, TAG,
                AppConfig.SPLASH_CONTENT_KEY,
                getString(R.string.splash_content));

        KJBitmap kjb = KJBitmap.create();
        kjb.display(mRlRoot, rootBgPath);
        kjb.display(mImgHead, headImgPath);
        kjb.display(mRlBox, boxBgPath);
        mTvContent.setText(contentStr);
    }
}

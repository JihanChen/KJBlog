package org.kymjs.blog.ui;

import org.kymjs.blog.AppConfig;
import org.kymjs.blog.AppContext;
import org.kymjs.blog.R;
import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.utils.DensityUtils;
import org.kymjs.kjframe.utils.PreferenceHelper;
import org.kymjs.push.core.KJPushManager;
import org.kymjs.push.core.PushReceiver;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * 开机界面
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class AppStart extends KJActivity {
    public static String TAG = "appstart";

    @Override
    public void setRootView() {
        ImageView image = new ImageView(aty);
        image.setImageResource(R.drawable.splash_bg);
        Animation anim = AnimationUtils.loadAnimation(aty, R.anim.splash_start);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                jumpTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        image.setAnimation(anim);
        setContentView(image);
        AppContext.screenH = DensityUtils.getScreenH(aty);
        AppContext.screenW = DensityUtils.getScreenW(aty);
    }

    /**
     * 根据设置启动推送进程
     */
    private void configPush() {
        boolean isOpen = PreferenceHelper.readBoolean(aty,
                AppConfig.PUSH_SWITCH_FILE, AppConfig.PUSH_SWITCH_KEY, true);
        if (isOpen) {
            KJPushManager.create().startWork(aty, PushReceiver.class);
        } else {
            KJPushManager.create().stopWork();
        }
    }

    private void jumpTo() {
        configPush();
        boolean isFirst = PreferenceHelper.readBoolean(aty, TAG, "first_open",
                true);
        Intent jumpIntent = new Intent();
        if (!isFirst) {
            jumpIntent.setClass(aty, Main.class);
        } else {
            PreferenceHelper.write(aty, TAG, "first_open", false);
            jumpIntent.setClass(aty, Splash.class);
        }
        startActivity(jumpIntent);
        finish();
    }
}

package org.kymjs.blog.ui;

import org.kymjs.blog.R;
import org.kymjs.kjframe.KJActivity;
import org.kymjs.kjframe.utils.PreferenceHelper;

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
    }

    private void jumpTo() {
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

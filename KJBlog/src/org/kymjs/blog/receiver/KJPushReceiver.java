package org.kymjs.blog.receiver;

import org.kymjs.kjframe.utils.SystemTool;
import org.kymjs.push.KJPushConfig;
import org.kymjs.push.core.KJPushManager;
import org.kymjs.push.core.PushReceiver;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

/**
 * 使用KJPush(http://git.oschina.net/kymjs/KJPush)项目，获取推送信息的广播，
 * 其中屏幕开关与网络改变的广播仅在应用未退出时有效
 * 
 * @author kymjs (http://github.com/kymjs)
 * 
 */
public class KJPushReceiver extends PushReceiver {

    Handler handle = new Handler() {};
    Runnable stopPush = new Runnable() {
        @Override
        public void run() {
            KJPushManager.create().stopWork();
        }
    };

    @Override
    public void onNetworkStateChange(Context context, Intent intent) {
        boolean isWifi = SystemTool.isWiFi(context);
        if (isWifi) {
            KJPushConfig.PALPITATE_TIME = 120000;
        } else {
            KJPushConfig.PALPITATE_TIME = 300000;
        }
    }

    @Override
    public void onScreenOff(Context context, Intent intent) {
        handle.postDelayed(stopPush, 300000);
    }

    @Override
    public void onScreenOn(Context context, Intent intent) {
        handle.removeCallbacks(stopPush);
        KJPushManager.create().startWork(context, KJPushReceiver.class);
    }

    @Override
    public void onPullData(Context context, Intent intent) {

    }
}

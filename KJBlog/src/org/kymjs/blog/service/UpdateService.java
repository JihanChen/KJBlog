package org.kymjs.blog.service;

import java.io.File;

import org.kymjs.blog.AppConfig;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.utils.FileUtils;

import android.app.IntentService;
import android.content.Intent;

public class UpdateService extends IntentService {

    private final KJHttp kjh;

    public UpdateService() {
        super("UpdateService");
        HttpConfig config = new HttpConfig();
        config.cacheTime = 0;
        kjh = new KJHttp(config);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        kjh.get("", new HttpCallBack() {});
    }

    private void download(String url) {
        File folder = FileUtils.getSaveFolder(AppConfig.saveFolder);
        File tempFile = new File(folder + "/kjblog.apk.tmp");
        if (tempFile.exists()) {
            tempFile.delete();
        }
        kjh.download(folder + "/kjblog.apk", url, new HttpCallBack() {});
    }
}

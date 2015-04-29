package org.kymjs.blog.service;

import java.io.File;

import org.kymjs.blog.AppConfig;
import org.kymjs.blog.utils.Parser;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.FileUtils;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.StringUtils;
import org.kymjs.kjframe.utils.SystemTool;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

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
        File file = new File(FileUtils.getSaveFolder(AppConfig.saveFolder)
                + "/kjblog.apk");
        if (file.exists()) {
            PackageManager pm = getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(file.getAbsolutePath(),
                    PackageManager.GET_ACTIVITIES);
            if (info.versionCode <= SystemTool.getAppVersionCode(this)) {
                file.delete();
            }
        }

        kjh.get("http://www.kymjs.com/api/version", new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                KJLoger.debug("检测更新===" + t);
                checkVersion(t);
            }
        });
    }

    private void checkVersion(String json) {
        final String url = Parser.checkVersion(UpdateService.this, json);
        if (!StringUtils.isEmpty(url) && SystemTool.isWiFi(this)) {
            download(url);
        }
    }

    private void download(String url) {
        final File folder = FileUtils.getSaveFolder(AppConfig.saveFolder);
        File tempFile = new File(folder + "/kjblog.apk.tmp");
        if (tempFile.exists()) {
            tempFile.delete();
        }
        ViewInject.toast("正在为你下载新版本");
        kjh.download(folder + "/kjblog.apk", url, new HttpCallBack() {
            /**
             * 下载过程
             */
            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
            }

            /**
             * 下载完成，开始安装
             */
            @Override
            public void onSuccess(byte[] t) {
                super.onSuccess(t);
                SystemTool.installApk(UpdateService.this, new File(folder
                        + "/kjblog.apk"));
            }
        });
    }
}

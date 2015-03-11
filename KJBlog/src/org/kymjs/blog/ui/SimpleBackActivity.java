package org.kymjs.blog.ui;

import org.kymjs.blog.R;
import org.kymjs.blog.domain.SimpleBackPage;
import org.kymjs.kjframe.ui.KJFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 应用二级界面
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 * 
 */
public class SimpleBackActivity extends TitleBarActivity {
    public static String TAG = SimpleBackActivity.class.getSimpleName();
    public static String CONTENT_KEY = "sba_content_key";
    public static String DATA_KEY = "sba_datat_key";

    @Override
    public void setRootView() {
        setContentView(R.layout.aty_simple_back);
        int value = getIntent().getIntExtra(CONTENT_KEY, -1);
        if (value != -1) {
            try {
                changeFragment((KJFragment) SimpleBackPage
                        .getPageByValue(value).newInstance());
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }
    }

    public Bundle getBundleData() {
        return getIntent().getBundleExtra(DATA_KEY);
    }

    public void changeFragment(KJFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }

    /**
     * 跳转到SimpleBackActivity时，只能使用该方法跳转
     * 
     * @param cxt
     * @param page
     * @param data
     */
    public static void postShowWith(Context cxt, SimpleBackPage page,
            Bundle data) {
        Intent intent = new Intent(cxt, SimpleBackActivity.class);
        intent.putExtra(CONTENT_KEY, page.getValue());
        intent.putExtra(DATA_KEY, data);
        cxt.startActivity(intent);
    }

    /**
     * 跳转到SimpleBackActivity时，只能使用该方法跳转
     * 
     * @param cxt
     * @param page
     */
    public static void postShowWith(Context cxt, SimpleBackPage page) {
        postShowWith(cxt, page, null);
    }
}

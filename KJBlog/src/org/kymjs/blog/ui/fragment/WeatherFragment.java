package org.kymjs.blog.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kymjs.blog.R;

/**
 * Created by lody  on 2015/3/13.
 */
public class WeatherFragment extends TitleBarFragment {

    //TODO:天气插件
    //state:未完成

    public static final String TAG = WeatherFragment.class.getSimpleName();

    private Activity aty;

    @Override
    protected View inflaterView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {

        aty = getActivity();
        View root = View.inflate(aty, R.layout.frag_wechat, null);
        return root;
    }


}

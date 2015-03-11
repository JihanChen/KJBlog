package org.kymjs.blog.ui.fragment;

import org.kymjs.blog.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 我爱看界面
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 * 
 */
public class MineFragment extends TitleBarFragment {

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View view = View.inflate(outsideAty, R.layout.frag_mine, null);
        return view;
    }
}

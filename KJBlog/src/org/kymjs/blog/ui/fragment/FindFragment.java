package org.kymjs.blog.ui.fragment;

import org.kymjs.blog.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 发现界面
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class FindFragment extends TitleBarFragment {

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.frag_find, null);
        return view;
    }
}

package org.kymjs.blog.ui.fragment;

import org.kymjs.blog.AppContext;
import org.kymjs.blog.R;
import org.kymjs.kjframe.ui.BindView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 发现界面
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class FindFragment extends TitleBarFragment {

    @BindView(id = R.id.find_img_zone)
    private ImageView mImgZone;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.frag_find, null);
        return view;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        LayoutParams params = (LayoutParams) mImgZone.getLayoutParams();
        params.height = (int) (AppContext.screenH * 0.3);
        params.width = AppContext.screenW;
        mImgZone.setLayoutParams(params);
    }
}

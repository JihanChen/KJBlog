package org.kymjs.blog.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import org.kymjs.blog.AppContext;
import org.kymjs.blog.R;
import org.kymjs.blog.domain.SimpleBackPage;
import org.kymjs.blog.ui.SimpleBackActivity;
import org.kymjs.blog.ui.TitleBarActivity;
import org.kymjs.blog.ui.widget.KJScrollView;
import org.kymjs.blog.ui.widget.KJScrollView.OnViewTopPull;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.widget.RoundImageView;

/**
 * 发现界面
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class FindFragment extends TitleBarFragment {

    @BindView(id = R.id.find_img_zone)
    private ImageView mImgZone;
    @BindView(id = R.id.find_img_head)
    private RoundImageView mImgHead;
    @BindView(id = R.id.find_tv_name)
    private TextView mTvName;
    @BindView(id = R.id.find_root)
    private KJScrollView rootView;
    @BindView(id = R.id.find_plugin_1, click = true)
    private TextView mTvWeather;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.frag_find, null);
        return view;
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        actionBarRes.title = getString(R.string.app_name);
        actionBarRes.menuImageId = R.drawable.titlebar_add;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        LayoutParams params = (LayoutParams) mImgZone.getLayoutParams();
        int h = params.height = (int) (AppContext.screenH * 0.3);
        params.width = AppContext.screenW;
        mImgZone.setLayoutParams(params);

        int space65 = (int) getResources().getDimension(R.dimen.space_65);

        LayoutParams headParams = (LayoutParams) mImgHead.getLayoutParams();
        headParams.topMargin = (h - space65) / 2 - 20;
        mImgHead.setLayoutParams(headParams);

        LayoutParams nameParams = (LayoutParams) mTvName.getLayoutParams();
        nameParams.topMargin = (h + space65) / 2;// 在头像底部间距半个头像的大小
        mTvName.setLayoutParams(nameParams);

        rootView.setOnViewTopPullListener(new OnViewTopPull() {
            @Override
            public void onPull() {
                if (outsideAty instanceof TitleBarActivity) {
                    outsideAty.getCurtainView().expand();
                }
            }
        });

    }

    @Override
    public void onMenuClick() {
        super.onMenuClick();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.find_plugin_1:
            SimpleBackActivity.postShowWith(outsideAty, SimpleBackPage.WEATHER);
            break;
        }
    }
}

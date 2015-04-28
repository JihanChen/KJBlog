package org.kymjs.blog.ui.fragment;

import org.kymjs.blog.R;
import org.kymjs.blog.ui.TitleBarActivity;
import org.kymjs.blog.ui.widget.KJScrollView;
import org.kymjs.blog.ui.widget.KJScrollView.OnViewTopPull;
import org.kymjs.blog.utils.UIHelper;
import org.kymjs.kjframe.ui.BindView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class AboutFragment extends TitleBarFragment {

    @BindView(id = R.id.use_help, click = true)
    private RelativeLayout mRlHelp;
    @BindView(id = R.id.version, click = true)
    private RelativeLayout mRlVersion;
    @BindView(id = R.id.about_me, click = true)
    private RelativeLayout mRlAuthor;
    @BindView(id = R.id.root)
    private KJScrollView rootView;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View view = View.inflate(outsideAty, R.layout.frag_about, null);
        return view;
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        actionBarRes.title = getString(R.string.about);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
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
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.use_help:
            UIHelper.toBrowser(outsideAty, "http://www.kymjs.com");
            break;
        case R.id.version:
            update();
            break;
        case R.id.about_me:
            UIHelper.toBrowser(outsideAty, "http://blog.kymjs.com/about/");
            break;

        default:
            break;
        }
    }

    private void update() {

    }
}

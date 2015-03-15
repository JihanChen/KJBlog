package org.kymjs.blog.ui.fragment;

import org.kymjs.blog.R;
import org.kymjs.kjframe.ui.BindView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * 
 * @author kymjs (https://github.com/kymjs)
 * 
 */
public class BlogDetailFragment extends TitleBarFragment {

    @BindView(id = R.id.blogdetail_webview)
    private WebView mWebView;
    @BindView(id = R.id.blogdetail_tv_author)
    private TextView mTvAuthor;
    @BindView(id = R.id.blogdetail_tv_time)
    private TextView mTvTime;
    @BindView(id = R.id.blogdetail_tv_title)
    private TextView mTvBlogTitle;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View root = View.inflate(outsideAty, R.layout.frag_blog_detail, null);
        return root;
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.title = getString(R.string.blog_detail);
        actionBarRes.backImageId = R.drawable.titlebar_back;
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        outsideAty.finish();
    }
}

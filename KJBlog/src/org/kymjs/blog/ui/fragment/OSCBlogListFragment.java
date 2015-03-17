package org.kymjs.blog.ui.fragment;

import org.kymjs.blog.R;
import org.kymjs.blog.adapter.OSCBlogAdapter;
import org.kymjs.blog.domain.OSCBlog;
import org.kymjs.blog.domain.OSCBlogList;
import org.kymjs.blog.domain.SimpleBackPage;
import org.kymjs.blog.ui.SimpleBackActivity;
import org.kymjs.blog.utils.Parser;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.ui.BindView;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 第三方博客列表
 * 
 * @author kymjs
 */
public class OSCBlogListFragment extends TitleBarFragment {

    @BindView(id = R.id.swiperefreshlayout)
    private SwipeRefreshLayout mRefreshLayout;
    @BindView(id = R.id.listview)
    private ListView mListView;

    private final String OSCBLOG_HOST = "http://www.oschina.net/action/api/userblog_list?authoruid=";
    private final int BLOGLIST_ID = 1428332;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View root = View.inflate(outsideAty,
                R.layout.frag_pull_refresh_listview, null);
        return root;
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                if (parent.getAdapter() instanceof OSCBlogAdapter) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("oscblog_id", ((OSCBlog) parent.getAdapter()
                            .getItem(position)).getId());
                    SimpleBackActivity.postShowWith(outsideAty,
                            SimpleBackPage.OSC_BLOG_DETAIL, bundle);
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        HttpConfig config = new HttpConfig();
        config.cacheTime = 30;
        KJHttp kjh = new KJHttp(config);
        kjh.get(OSCBLOG_HOST + BLOGLIST_ID, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                OSCBlogList dataRes = Parser.xmlToBean(OSCBlogList.class, t);
                mListView.setAdapter(new OSCBlogAdapter(outsideAty, dataRes
                        .getBloglist()));
            }
        });
    }
}

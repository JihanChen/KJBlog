package org.kymjs.blog.ui.fragment;

import java.util.List;
import java.util.TreeSet;

import org.kymjs.blog.R;
import org.kymjs.blog.adapter.TweetAdapter;
import org.kymjs.blog.domain.Tweet;
import org.kymjs.blog.domain.TweetsList;
import org.kymjs.blog.ui.widget.listview.PullToRefreshBase;
import org.kymjs.blog.ui.widget.listview.PullToRefreshBase.OnRefreshListener;
import org.kymjs.blog.ui.widget.listview.PullToRefreshList;
import org.kymjs.blog.utils.Parser;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.ui.BindView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * [我要吐槽]界面
 * 
 * @author kymjs
 * 
 */
public class TweetFragment extends TitleBarFragment {

    @BindView(id = R.id.listview)
    private PullToRefreshList mRefreshLayout;
    private ListView mListView;

    private TweetAdapter adapter;

    private KJHttp kjh;
    private final TreeSet<Tweet> tweets = new TreeSet<Tweet>();
    private final String OSCTWEET_HOST = "http://www.oschina.net/action/api/tweet_list";

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View root = View.inflate(outsideAty,
                R.layout.frag_pull_refresh_listview, null);
        return root;
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.title = getString(R.string.today_message);
        actionBarRes.backImageId = R.drawable.titlebar_back;
    }

    @Override
    protected void initData() {
        super.initData();
        HttpConfig config = new HttpConfig();
        config.cacheTime = 0;
        config.useDelayCache = false;
        kjh = new KJHttp(config);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        mListView = mRefreshLayout.getRefreshView();
        mListView.setDivider(new ColorDrawable(android.R.color.transparent));
        mListView.setSelector(new ColorDrawable(android.R.color.transparent));
        mRefreshLayout.setPullLoadEnabled(true);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                refresh(0);
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                refresh();
            }
        });

        fillUI();
    }

    /**
     * 首次进入时填充数据
     */
    private void fillUI() {
        refresh();
    }

    /**
     * 刷新
     * 
     * @param page
     *            请求第几页数据
     */
    private void refresh() {
        double page = tweets.size() / 20;
        if (page % 1 != 0) {
            page += 1;
        }
        refresh((int) page);
    }

    private void refresh(int page) {
        HttpParams params = new HttpParams();
        params.put("pageIndex", page);
        params.put("pageSize", "20");
        kjh.get(OSCTWEET_HOST, params, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                mRefreshLayout.onPullDownRefreshComplete();
                List<Tweet> datas = Parser.xmlToBean(TweetsList.class, t)
                        .getList();
                tweets.addAll(datas);
                if (adapter == null) {
                    adapter = new TweetAdapter(outsideAty, tweets);
                    mListView.setAdapter(adapter);
                } else {
                    adapter.refresh(tweets);
                }
                mRefreshLayout.onPullDownRefreshComplete();
                mRefreshLayout.onPullUpRefreshComplete();
            }
        });
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        outsideAty.finish();
    }
}

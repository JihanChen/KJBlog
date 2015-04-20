package org.kymjs.blog.ui.fragment;

import org.kymjs.blog.R;
import org.kymjs.blog.adapter.ActiveAdapter;
import org.kymjs.blog.domain.Active;
import org.kymjs.blog.domain.ActiveList;
import org.kymjs.blog.ui.Browser;
import org.kymjs.blog.ui.widget.listview.FooterLoadingLayout;
import org.kymjs.blog.ui.widget.listview.PullToRefreshBase;
import org.kymjs.blog.ui.widget.listview.PullToRefreshBase.OnRefreshListener;
import org.kymjs.blog.ui.widget.listview.PullToRefreshList;
import org.kymjs.blog.utils.Parser;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.StringUtils;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * [猿活动]界面
 * 
 * @author kymjs
 * 
 */
public class ActiveFragment extends TitleBarFragment implements
        OnItemClickListener {

    public static final String TAG = ActiveFragment.class.getSimpleName();

    @BindView(id = R.id.listview)
    private PullToRefreshList mRefreshLayout;
    private ListView mListView;

    private ActiveAdapter adapter;

    private KJHttp kjh;
    private final String ACTIVE_HOST = "http://www.oschina.net/action/api/event_list";
    private String cache;

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
        actionBarRes.title = getString(R.string.str_active_title);
        actionBarRes.backImageId = R.drawable.titlebar_back;
        actionBarRes.menuImageId = R.drawable.titlebar_add;
    }

    @Override
    protected void initData() {
        super.initData();
        HttpConfig config = new HttpConfig();
        config.useDelayCache = false;
        kjh = KJHttp.create(config);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        mListView = mRefreshLayout.getRefreshView();
        mListView.setDivider(new ColorDrawable(android.R.color.transparent));
        mListView.setSelector(new ColorDrawable(android.R.color.transparent));
        mListView.setOnItemClickListener(this);
        mRefreshLayout.setPullLoadEnabled(true);
        ((FooterLoadingLayout) mRefreshLayout.getFooterLoadingLayout())
                .setNoMoreDataText("暂时还没有其他活动");
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                refresh();
            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {
                mRefreshLayout.setHasMoreData(false);
            }
        });
        fillUI();
    }

    /**
     * 首次进入时填充数据
     */
    private void fillUI() {
        cache = kjh.getStringCache(ACTIVE_HOST);
        if (!StringUtils.isEmpty(cache)) {
            ActiveList dataRes = Parser.xmlToBean(ActiveList.class, cache);
            if (adapter == null) {
                adapter = new ActiveAdapter(outsideAty, dataRes.getEvents());
                mListView.setAdapter(adapter);
            } else {
                adapter.refresh(dataRes.getEvents());
            }
        }
        refresh();
    }

    private void refresh() {
        kjh.get(ACTIVE_HOST, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                KJLoger.debug(TAG + "网络请求：" + t);
                mRefreshLayout.onPullDownRefreshComplete();
                if (t != null && !t.equals(cache)) {
                    ActiveList dataRes = Parser.xmlToBean(ActiveList.class, t);
                    if (adapter == null) {
                        adapter = new ActiveAdapter(outsideAty, dataRes
                                .getEvents());
                        mListView.setAdapter(adapter);
                    } else {
                        adapter.refresh(dataRes.getEvents());
                    }
                }
            }
        });
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        outsideAty.finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        Intent it = new Intent(outsideAty, Browser.class);
        it.putExtra(Browser.BROWSER_KEY,
                ((Active) adapter.getItem(position)).getUrl());
        it.putExtra(Browser.BROWSER_TITLE_KEY,
                ((Active) adapter.getItem(position)).getTitle());
        outsideAty.showActivity(outsideAty, it);
    }
}

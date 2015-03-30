package org.kymjs.blog.ui.fragment;

import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.adapter.BlogAdapter;
import org.kymjs.blog.domain.Blog;
import org.kymjs.blog.domain.SimpleBackPage;
import org.kymjs.blog.ui.Main;
import org.kymjs.blog.ui.SimpleBackActivity;
import org.kymjs.blog.ui.widget.listview.PullToRefreshBase;
import org.kymjs.blog.ui.widget.listview.PullToRefreshBase.OnRefreshListener;
import org.kymjs.blog.ui.widget.listview.PullToRefreshListView;
import org.kymjs.blog.ui.widget.listview.StickyHeadListView;
import org.kymjs.blog.ui.widget.listview.StickyHeadListView.OnHeadChangeListener;
import org.kymjs.blog.ui.widget.listview.StickyHeadListView.OnHeaderClickListener;
import org.kymjs.blog.utils.Parser;
import org.kymjs.blog.utils.UIHelper;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.PreferenceHelper;
import org.kymjs.kjframe.utils.StringUtils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 主界面博客模块
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class BlogFragment extends TitleBarFragment {

    public static final String TAG = BlogFragment.class.getSimpleName();

    @BindView(id = R.id.blog_swiperefreshlayout)
    private PullToRefreshListView mRefreshLayout;
    private StickyHeadListView mFakeList;
    private ListView mList;

    private Main aty;
    private KJHttp kjh;
    private BlogAdapter adapter;

    private boolean showStickyHead = true;
    private final String MY_BLOG_HOST = "http://www.kymjs.com/json_blog_list";
    private String cache;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        aty = (Main) getActivity();
        return View.inflate(aty, R.layout.frag_blog, null);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        actionBarRes.title = getString(R.string.app_name);
    }

    @Override
    protected void initData() {
        super.initData();
        boolean isClickedMessage = PreferenceHelper.readBoolean(aty, TAG,
                "is_clicked_msg", false);
        boolean isCurrentDate = (PreferenceHelper.readString(aty, TAG,
                "prev_click_date", "")).equals(StringUtils
                .getDataTime("yyyy-MM-dd"));
        showStickyHead = !isClickedMessage || !isCurrentDate;

        HttpConfig config = new HttpConfig();
        int hour = StringUtils.toInt(StringUtils.getDataTime("HH"), 0);
        if (hour > 12 && hour < 22) {
            config.cacheTime = 10;
        } else {
            config.cacheTime = 300;
        }
        config.useDelayCache = true;
        kjh = new KJHttp(config);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        listViewPreference();
        fillUI();
    }

    /**
     * 初始化ListView样式
     */
    private void listViewPreference() {
        mFakeList = mRefreshLayout.getRefreshView();
        mFakeList.setDivider(null);
        mFakeList.setDrawingListUnderStickyHeader(true);
        mFakeList.setAreHeadersSticky(true);
        mList = mFakeList.getWrappedList();
        mList.setDivider(null);
        mList.setOverscrollFooter(null);
        mList.setOverscrollHeader(null);
        mList.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
        mFakeList.setOpenStickyHead(showStickyHead);
        mRefreshLayout.setPullLoadEnabled(true);

        mFakeList.setOnHeadChangeListener(new OnHeadChangeListener() {
            @Override
            public void onStickyHeaderOffsetChanged(StickyHeadListView l,
                    View header, int offset) {
                if (!showStickyHead) {
                    return;
                }
                ((TextView) ((RelativeLayout) header).getChildAt(1))
                        .setTextColor(0xff000000);
                ((RelativeLayout) header).getChildAt(2)
                        .setVisibility(View.GONE);
                ((RelativeLayout) header).getChildAt(3)
                        .setVisibility(View.GONE);
                header.setBackgroundResource(R.color.titlebar_color);
            }

            @Override
            public void onStickyHeaderChanged(StickyHeadListView l,
                    View header, int itemPosition, String headerId) {}
        });

        mFakeList.setOnHeaderClickListener(new OnHeaderClickListener() {
            @Override
            public void onHeaderClick(StickyHeadListView l, View header,
                    int itemPosition, String headerId, boolean currentlySticky) {
                SimpleBackActivity.postShowWith(aty, SimpleBackPage.COMMENT);
                PreferenceHelper.write(aty, TAG, "is_clicked_msg", true);
                PreferenceHelper.write(aty, TAG, "prev_click_date",
                        StringUtils.getDataTime("yyyy-MM-dd"));
                mFakeList.setOpenStickyHead(false);
            }
        });
        mFakeList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                UIHelper.toBrowser(aty,
                        ((Blog) adapter.getItem(position)).getUrl());
            }
        });

        mRefreshLayout
                .setOnRefreshListener(new OnRefreshListener<StickyHeadListView>() {
                    @Override
                    public void onPullDownToRefresh(
                            PullToRefreshBase<StickyHeadListView> refreshView) {
                        refresh();
                    }

                    @Override
                    public void onPullUpToRefresh(
                            PullToRefreshBase<StickyHeadListView> refreshView) {
                        refresh();
                    }
                });
    }

    private void fillUI() {
        cache = kjh.getCache(MY_BLOG_HOST, null);
        if (!StringUtils.isEmpty(cache)) {
            List<Blog> datas = Parser.getBlogList(cache);
            adapter = new BlogAdapter(aty, datas);
            mList.setAdapter(adapter);
        }
        refresh();
    }

    private void refresh() {
        kjh.get(MY_BLOG_HOST, new HttpCallBack() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                if (t != null) {
                    List<Blog> datas = Parser.getBlogList(t);
                    if (adapter == null) {
                        adapter = new BlogAdapter(outsideAty, datas);
                        mList.setAdapter(adapter);
                    } else {
                        adapter.refresh(datas);
                    }
                }
                mRefreshLayout.onPullDownRefreshComplete();
                mRefreshLayout.onPullUpRefreshComplete();
            }
        });
    }
}

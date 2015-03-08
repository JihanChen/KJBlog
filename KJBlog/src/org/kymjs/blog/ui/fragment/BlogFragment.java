package org.kymjs.blog.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.adapter.BlogAdapter;
import org.kymjs.blog.domain.Blog;
import org.kymjs.blog.domain.SimpleBackPage;
import org.kymjs.blog.ui.Main;
import org.kymjs.blog.ui.SimpleBackActivity;
import org.kymjs.blog.ui.widget.listview.PullToRefreshListView;
import org.kymjs.blog.ui.widget.listview.StickyHeadListView;
import org.kymjs.blog.ui.widget.listview.StickyHeadListView.OnHeadChangeListener;
import org.kymjs.blog.ui.widget.listview.StickyHeadListView.OnHeaderClickListener;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.utils.PreferenceHelper;
import org.kymjs.kjframe.utils.StringUtils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private boolean showStickyHead = true;
    boolean issss = false;

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
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        listViewPreference();

        List<Blog> datas = new ArrayList<Blog>();
        for (int i = 0; i < 30; i++) {
            Blog blog = new Blog();
            blog.setId(i + "");
            blog.setIsAuthor(i % 3);
            blog.setIsRecommend(i % 4);
            blog.setDate(StringUtils.getDataTime("yyyy-MM-dd"));
            blog.setDescription("测试数据测试数据测试数据测试数据测试数据测试数据测试数据");
            blog.setTitle("第" + i + "篇博客");
            if (i % 2 == 0) {
                blog.setImageUrl("http://static.oschina.net/uploads/space/2015/0306/225310_wFPo_5189.jpg");
            }
            datas.add(blog);
        }

        mFakeList.setAdapter(new BlogAdapter(aty, datas));
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
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}

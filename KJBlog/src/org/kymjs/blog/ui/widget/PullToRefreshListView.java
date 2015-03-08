package org.kymjs.blog.ui.widget;

import org.kymjs.blog.ui.widget.ILoadingLayout.State;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.ListView;

/**
 * 尊重原创感谢开源：本控件是由chrisbanes的Android-PullToRefresh(https://github.com/chrisbanes/
 * Android-PullToRefresh)项目修改而来，主要是根据项目需求做了精简只保留需要部分<br>
 * 
 * 这个类实现了ListView下拉刷新，上加载更多和滑到底部自动加载
 * 
 * @author chrisbanes (https://github.com/chrisbanes)
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class PullToRefreshListView extends
        PullToRefreshBase<StickyHeadListView> implements OnScrollListener {

    private StickyHeadListView mfakeListView; // 一个伪造的ListView(实际上是包含了一个ListView)
    private LoadingLayout mLoadMoreFooterLayout; // 用于滑到底部自动加载的Footer
    private OnScrollListener mScrollListener; // 滚动的监听器

    public PullToRefreshListView(Context context) {
        this(context, null);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);
        setPullLoadEnabled(false);
    }

    @Override
    protected StickyHeadListView createRefreshView(Context context,
            AttributeSet attrs) {
        StickyHeadListView listView = new StickyHeadListView(context);
        mfakeListView = listView;
        listView.setOnScrollListener(this);
        return listView;
    }

    /**
     * 设置是否有更多数据的标志
     * 
     * @param hasMoreData
     *            true表示还有更多的数据，false表示没有更多数据了
     */
    public void setHasMoreData(boolean hasMoreData) {
        if (!hasMoreData) {
            if (null != mLoadMoreFooterLayout) {
                mLoadMoreFooterLayout.setState(State.NO_MORE_DATA);
            }

            LoadingLayout footerLoadingLayout = getFooterLoadingLayout();
            if (null != footerLoadingLayout) {
                footerLoadingLayout.setState(State.NO_MORE_DATA);
            }
        }
    }

    /**
     * 设置滑动的监听器
     */
    public void setOnScrollListener(OnScrollListener l) {
        mScrollListener = l;
    }

    @Override
    protected boolean isReadyForPullUp() {
        return isLastItemVisible();
    }

    @Override
    protected boolean isReadyForPullDown() {
        return isFirstItemVisible();
    }

    @Override
    protected void startLoading() {
        super.startLoading();

        if (null != mLoadMoreFooterLayout) {
            mLoadMoreFooterLayout.setState(State.REFRESHING);
        }
    }

    @Override
    public void onPullUpRefreshComplete() {
        super.onPullUpRefreshComplete();

        if (null != mLoadMoreFooterLayout) {
            mLoadMoreFooterLayout.setState(State.RESET);
        }
    }

    @Override
    public void setScrollLoadEnabled(boolean scrollLoadEnabled) {
        super.setScrollLoadEnabled(scrollLoadEnabled);

        if (scrollLoadEnabled) {
            // 设置Footer
            if (null == mLoadMoreFooterLayout) {
                mLoadMoreFooterLayout = new FooterLoadingLayout(getContext());
            }

            if (null == mLoadMoreFooterLayout.getParent()) {
                mfakeListView.addFooterView(mLoadMoreFooterLayout, null, false);
            }
            mLoadMoreFooterLayout.show(true);
        } else {
            if (null != mLoadMoreFooterLayout) {
                mLoadMoreFooterLayout.show(false);
            }
        }
    }

    @Override
    public LoadingLayout getFooterLoadingLayout() {
        if (isScrollLoadEnabled()) {
            return mLoadMoreFooterLayout;
        }

        return super.getFooterLoadingLayout();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isScrollLoadEnabled() && hasMoreData()) {
            if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
                    || scrollState == OnScrollListener.SCROLL_STATE_FLING) {
                if (isReadyForPullUp()) {
                    startLoading();
                }
            }
        }

        if (null != mScrollListener) {
            mScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {
        if (null != mScrollListener) {
            mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
                    totalItemCount);
        }
    }

    @Override
    protected LoadingLayout createHeaderLoadingLayout(Context context,
            AttributeSet attrs) {
        return new HeaderLoadingLayout(context);
    }

    /**
     * 表示是否还有更多数据
     * 
     * @return true表示还有更多数据
     */
    private boolean hasMoreData() {
        if ((null != mLoadMoreFooterLayout)
                && (mLoadMoreFooterLayout.getState() == State.NO_MORE_DATA)) {
            return false;
        }

        return true;
    }

    /**
     * 判断第一个child是否完全显示出来
     * 
     * @return true完全显示出来，否则false
     */
    private boolean isFirstItemVisible() {
        final Adapter adapter = mfakeListView.getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            return true;
        }

        // kymjs
        // 这里把0改为1的目的是因为使用了stackyListView，头部有一个广告条
        // int mostTop = (mListView.getChildCount() > 0) ?
        // mListView.getChildAt(0)
        ListView listview = mfakeListView.getWrappedList();

        int mostTop = (listview.getChildCount() > 0) ? listview.getChildAt(0)
                .getTop() : listview.getTop();
        if (mostTop >= 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断最后一个child是否完全显示出来
     * 
     * @return true完全显示出来，否则false
     */
    private boolean isLastItemVisible() {
        final Adapter adapter = mfakeListView.getAdapter();

        if (null == adapter || adapter.isEmpty()) {
            return true;
        }

        final int lastItemPosition = adapter.getCount() - 1;
        final int lastVisiblePosition = mfakeListView.getLastVisiblePosition();

        /**
         * This check should really just be: lastVisiblePosition ==
         * lastItemPosition, but ListView internally uses a FooterView which
         * messes the positions up. For me we'll just subtract one to account
         * for it and rely on the inner condition which checks getBottom().
         */
        if (lastVisiblePosition >= lastItemPosition - 1) {
            final int childIndex = lastVisiblePosition
                    - mfakeListView.getFirstVisiblePosition();
            final int childCount = mfakeListView.getChildCount();
            final int index = Math.min(childIndex, childCount - 1);
            final View lastVisibleChild = mfakeListView.getChildAt(index);
            if (lastVisibleChild != null) {
                return lastVisibleChild.getBottom() <= mfakeListView
                        .getBottom();
            }
        }

        return false;
    }
}

package org.kymjs.blog.ui.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.TreeSet;

import org.kymjs.blog.R;
import org.kymjs.blog.adapter.TweetAdapter;
import org.kymjs.blog.domain.SimpleBackPage;
import org.kymjs.blog.domain.Tweet;
import org.kymjs.blog.domain.TweetsList;
import org.kymjs.blog.ui.SimpleBackActivity;
import org.kymjs.blog.ui.widget.listview.PullToRefreshBase;
import org.kymjs.blog.ui.widget.listview.PullToRefreshBase.OnRefreshListener;
import org.kymjs.blog.ui.widget.listview.PullToRefreshList;
import org.kymjs.blog.utils.Parser;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.ui.BindView;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.KJLoger;
import org.kymjs.kjframe.utils.StringUtils;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
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

    public static final int REQUEST_CODE_RECORD = 1;
    public static final String CONTENT_KEY = "Tweet_content_key";
    public static final String AUDIOPATH_KEY = "Tweet_audiopath_key";
    public static final String IMAGEPATH_KEY = "Tweet_imagepath_key";

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
        actionBarRes.title = getString(R.string.str_tweet_title);
        actionBarRes.backImageId = R.drawable.titlebar_back;
        actionBarRes.menuImageId = R.drawable.titlebar_add;
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

    @Override
    public void onMenuClick() {
        super.onMenuClick();
        SimpleBackActivity.postShowForResult(this, 1,
                SimpleBackPage.OSC_TWEET_SEND);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (resultCode) {
        case REQUEST_CODE_RECORD:
            handleSubmit(data.getStringExtra(CONTENT_KEY), null,
                    data.getStringExtra(AUDIOPATH_KEY));
            break;

        default:
            break;
        }
    }

    /**
     * 发布动弹
     */
    private void handleSubmit(String strSpeech, String imageFilePath,
            String audioPath) {
        if (StringUtils.isEmpty(strSpeech)) {
            ViewInject.toast("不能发布空吐槽");
            return;
        }
        Tweet tweet = new Tweet();
        tweet.setAuthorid(2332925);
        tweet.setAudioPath(audioPath);
        tweet.setImageFilePath(imageFilePath);
        tweet.setBody(strSpeech + "————来自[爱看博客]Android客户端");

        HttpConfig config = new HttpConfig();
        config.cacheTime = 0;
        config.setCookieString("oscid=8N57Os9FG%2F%2B%2FFIA9vyogCJYPf0yMQGHmZhyzKMyuza2hL%2BW4xL7DPVVS%2B1BREZZzJGVMZrm4jNnkRHJmiDzNhjZIjp4pKbDtS4hUVFfAysLMq%2Fy5vIojQA%3D%3D;JSESSIONID=9B7tJ9RSZ4YYbdRhvg2xcTQ7skNJBwK3tMzdttnZwJpqmtx1d6hn!-25520330;");
        KJHttp kjh = new KJHttp(config);
        HttpParams params = new HttpParams();
        params.put("uid", tweet.getAuthorid());
        params.put("msg", tweet.getBody());

        if (!TextUtils.isEmpty(tweet.getImageFilePath())) {
            try {
                params.put("img", new File(tweet.getImageFilePath()));
            } catch (FileNotFoundException e) {
            }
        }
        if (!StringUtils.isEmpty(tweet.getAudioPath())) {
            try {
                params.put("amr", new File(tweet.getAudioPath()));
            } catch (FileNotFoundException e) {
            }
        }
        kjh.post("http://www.oschina.net/action/api/tweet_pub", params,
                new HttpCallBack() {
                    @Override
                    public void onPreStart() {
                        super.onPreStart();
                        // 设置上传动画
                    }

                    @Override
                    public void onSuccess(String t) {
                        super.onSuccess(t);
                        KJLoger.debug(t);
                        // 隐藏上传动画
                    }

                    @Override
                    public void onFailure(Throwable t, int errorNo,
                            String strMsg) {
                        super.onFailure(t, errorNo, strMsg);
                        // 设置上传动画失败图标
                    }
                });

    }
}

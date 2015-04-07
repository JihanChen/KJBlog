package org.kymjs.blog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author HuangWenwei
 * 
 * @date 2014年10月10日
 */
@SuppressWarnings("serial")
@XStreamAlias("oschina")
public class TweetsList implements Serializable {
    @XStreamAlias("id")
    private int id;
    @XStreamAlias("tweetCount")
    private int tweetCount;
    @XStreamAlias("pagesize")
    private int pagesize;
    @XStreamAlias("tweets")
    private List<Tweet> tweetslist = new ArrayList<Tweet>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTweetCount() {
        return tweetCount;
    }

    public void setTweetCount(int tweetCount) {
        this.tweetCount = tweetCount;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public List<Tweet> getTweetslist() {
        return tweetslist;
    }

    public void setTweetslist(List<Tweet> tweetslist) {
        this.tweetslist = tweetslist;
    }

    public List<Tweet> getList() {
        return tweetslist;
    }

}

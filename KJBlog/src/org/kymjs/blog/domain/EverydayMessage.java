package org.kymjs.blog.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 每日推荐的javabean
 * 
 * @author kymjs (https://github.com/kymjs)
 * @since 2015-3
 */
public class EverydayMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id; // 每日推送id
    private List<String> urlList; // 每日推送地址集
    private List<String> titleList; // 每日推送标题集
    private List<String> imageUrlList; // 每日推送图片集
    private String date; // 当日日期 yyyy-MM-dd
    private String time; // 时间 hh:mm

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

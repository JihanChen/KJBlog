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
    private String title; // 单图文标题
    private String description; // 单图文描述
    private String imgUrl; // 单图文图片链接
    private String url; // 单图文链接
    private boolean hasItem; // 是否多图文

    private List<String> urlList; // 每日推送地址集
    private List<String> titleList; // 每日推送标题集
    private List<String> imageUrlList; // 每日推送图片集
    private String time; // 当日日期 yyyy-MM-dd hh:mm

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isHasItem() {
        return hasItem;
    }

    public void setHasItem(boolean hasItem) {
        this.hasItem = hasItem;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

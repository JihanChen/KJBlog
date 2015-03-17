package org.kymjs.blog.domain;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author HuangWenwei
 * 
 * @date 2014年9月29日
 */
@SuppressWarnings("serial")
@XStreamAlias("blog")
public class OSCBlog implements Serializable {

    @XStreamAlias("id")
    private int id;

    @XStreamAlias("title")
    private String title;

    @XStreamAlias("url")
    private String url;

    @XStreamAlias("where")
    private String where;

    @XStreamAlias("commentCount")
    private int commentCount;

    @XStreamAlias("body")
    private String body;

    @XStreamAlias("author")
    private String authorname;

    @XStreamAlias("authorid")
    private int authoruid;

    @XStreamAlias("documentType")
    private int documentType;

    @XStreamAlias("pubDate")
    private String pubDate;

    @XStreamAlias("favorite")
    private int favorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public int getAuthoruid() {
        return authoruid;
    }

    public void setAuthoruid(int authoruid) {
        this.authoruid = authoruid;
    }

    public int getDocumentType() {
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

}

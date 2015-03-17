package org.kymjs.blog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author HuangWenwei
 * 
 * @date 2014年9月28日
 */
@SuppressWarnings("serial")
@XStreamAlias("oschina")
public class OSCBlogList implements Serializable {
    @XStreamAlias("id")
    private int id;

    @XStreamAlias("pagesize")
    private int pagesize;

    @XStreamAlias("blogs")
    private List<OSCBlog> bloglist = new ArrayList<OSCBlog>();

    @XStreamAlias("blogsCount")
    private int blogsCount;

    public int getPageSize() {
        return pagesize;
    }

    public void setPageSize(int pageSize) {
        this.pagesize = pageSize;
    }

    public List<OSCBlog> getBloglist() {
        return bloglist;
    }

    public void setBloglist(List<OSCBlog> bloglist) {
        this.bloglist = bloglist;
    }

    public List<OSCBlog> getList() {
        return bloglist;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getBlogsCount() {
        return blogsCount;
    }

    public void setBlogsCount(int blogsCount) {
        this.blogsCount = blogsCount;
    }
}

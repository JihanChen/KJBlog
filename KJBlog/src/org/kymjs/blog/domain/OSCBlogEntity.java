package org.kymjs.blog.domain;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("oschina")
public class OSCBlogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @XStreamAlias("blog")
    private OSCBlog blog;

    public OSCBlog getBlog() {
        return blog;
    }

    public void setBlog(OSCBlog blog) {
        this.blog = blog;
    }

}

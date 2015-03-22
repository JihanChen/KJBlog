package org.kymjs.blog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 活动实体类列表
 * 
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2014年12月10日 下午2:28:54
 * 
 */
@SuppressWarnings("serial")
@XStreamAlias("oschina")
public class ActiveList implements Serializable {

    @XStreamAlias("pagesize")
    private int pagesize;

    @XStreamAlias("events")
    private final List<Active> events = new ArrayList<Active>();

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public List<Active> getEvents() {
        return events;
    }

}

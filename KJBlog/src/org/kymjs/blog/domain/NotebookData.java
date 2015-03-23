package org.kymjs.blog.domain;

import java.io.Serializable;

import org.kymjs.kjframe.database.annotate.Id;

/**
 * 便签数据bean（有重载equals()方法）
 * 
 * @author kymjs (https://github.com/kymjs)
 * 
 */
@SuppressWarnings("serial")
public class NotebookData implements Serializable {

    @Id
    private int id;
    private String date;
    private String content;
    private int color;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
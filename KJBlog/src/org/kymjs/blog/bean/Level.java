package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lody  on 2015/3/13.
 */
public class Level {
    @SerializedName("code")
    public String code;
    @SerializedName("type")
    public String type;
    @SerializedName("woid")
    public String woid;
    @SerializedName("content")
    public String content;
}

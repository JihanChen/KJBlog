package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thinkfeed#gmail.com on 13-6-21.
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

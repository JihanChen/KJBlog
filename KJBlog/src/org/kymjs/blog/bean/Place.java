package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lody  on 2015/3/13.
 */
public class Place {
    @SerializedName("lang")
    public String lang;
    @SerializedName("uri")
    public String uri;
    @SerializedName("woid")
    public String woid;
    @SerializedName("name")
    public String name;
    @SerializedName("country")
    public Level country = new Level();
    @SerializedName("admin1")
    public Level admin1 = new Level();
    @SerializedName("admin2")
    public Level admin2 = new Level();
    @SerializedName("admin3")
    public Level admin3 = new Level();
}

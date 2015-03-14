package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by think on 13-5-18.
 */
public class Coord {
    @SerializedName("lon")
    public double lon;
    @SerializedName("lat")
    public double lat;

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }
}

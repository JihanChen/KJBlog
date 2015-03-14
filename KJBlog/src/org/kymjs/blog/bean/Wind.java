package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lody  on 2015/3/13.
 */
public class Wind {
    @SerializedName("speed")
    public double speed;
    @SerializedName("deg")
    public double deg;

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }
}

package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lody  on 2015/3/13.
 */
public class Temp {
    @SerializedName("day")
    public double day;
    @SerializedName("max")
    public double max;
    @SerializedName("min")
    public double min;
    @SerializedName("night")
    public double night;
    @SerializedName("eve")
    public double eve;
    @SerializedName("morn")
    public double morn;

    public double getDay() {
        return day;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public double getNight() {
        return night;
    }

    public double getEve() {
        return eve;
    }

    public double getMorn() {
        return morn;
    }
}


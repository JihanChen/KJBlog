package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lody  on 2015/3/13.
 */
public class Main {
    @SerializedName("temp")
    public double temp;
    @SerializedName("humidity")
    public double humidity;
    @SerializedName("pressure")
    public double pressure;
    @SerializedName("temp_min")
    public double temp_min;
    @SerializedName("temp_max")
    public double temp_max;

    public double getTemp() {
        return temp;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }
}

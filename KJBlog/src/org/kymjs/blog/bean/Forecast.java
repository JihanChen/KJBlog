package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkfeed#gmail.com on 13-5-28.
 */
public class Forecast {
    @SerializedName("dt")
    public long dt;
    @SerializedName("temp")
    public Temp temp = new Temp();
    @SerializedName("pressure")
    public double pressure;
    @SerializedName("humidity")
    public double humidity;
    @SerializedName("weather")
    public List<Weather> weather = new ArrayList<Weather>();
    @SerializedName("speed")
    public double speed;
    @SerializedName("deg")
    public double deg;
    @SerializedName("clouds")
    public double clouds;
    @SerializedName("rain")
    public double rain;

    public long getDt() {
        return dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

    public double getClouds() {
        return clouds;
    }

    public double getRain() {
        return rain;
    }
}

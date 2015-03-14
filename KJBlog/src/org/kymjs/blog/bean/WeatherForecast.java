package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lody  on 2015/3/13.
 */
public class WeatherForecast {
    @SerializedName("cod")
    public int cod;
    @SerializedName("message")
    public String message;
    @SerializedName("cnt")
    public int cnt;
    @SerializedName("city")
    public City city = new City();
    @SerializedName("list")
    public List<Forecast> forecasts = new ArrayList<Forecast>();

    public int getCod() {
        return cod;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public int getCnt() {
        return cnt;
    }

    public City getCity() {
        return city;
    }

    public String getMessage() {
        return message;
    }
}

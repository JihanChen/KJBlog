package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lody  on 2015/3/13.
 */
public class WeatherToday {
    @SerializedName("coord")
    public Coord coord = new Coord();
    @SerializedName("sys")
    public Sys sys = new Sys();
    @SerializedName("weather")
    public List<Weather> weather = new ArrayList<Weather>();
    @SerializedName("base")
    public String base;
    @SerializedName("main")
    public Main main = new Main();
    @SerializedName("wind")
    public Wind wind = new Wind();
    @SerializedName("clouds")
    public Clounds clounds = new Clounds();
    @SerializedName("dt")
    public String dt;
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("cod")
    public int cod;

    public Coord getCoord() {
        return coord;
    }

    public int getCode() {
        return cod;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDt() {
        return dt;
    }

    public Clounds getClounds() {
        return clounds;
    }

    public Wind getWind() {
        return wind;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Sys getSys() {
        return sys;
    }
}

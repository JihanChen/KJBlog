package org.kymjs.blog.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lody  on 2015/3/13.
 */
public class City {
    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("country")
    public String country;
    @SerializedName("population")
    public String population;
    @SerializedName("coord")
    public Coord coord = new Coord();

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getPopulation() {
        return population;
    }

    public Coord getCoord() {
        return coord;
    }
}

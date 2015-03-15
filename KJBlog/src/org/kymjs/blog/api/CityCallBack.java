package org.kymjs.blog.api;

import org.kymjs.blog.bean.Place;

import java.util.List;

/**
 * Created by lody  on 2015/3/15.
 */
public interface CityCallBack {
    void onCitySearchSuccess(List<Place> places);
    void onCitySearchFailture(String reason);

}

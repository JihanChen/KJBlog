package org.kymjs.blog.domain;

/**
 * Created by lody  on 2015/3/13.
 */

import org.kymjs.blog.bean.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于订阅事件
 * 当搜索城市完成后，此事件将被发送给订阅者。
 */
public class SearchCityEvent {
    private List<Place> places = null;//保存地点信息

    public SearchCityEvent(List<Place> places) {
        this.places = places;
    }

    /**
     * @return 地点信息
     */
    public List<Place> getPlaces() {

        return this.places != null ? new ArrayList<Place>() : this.places;

    }
}

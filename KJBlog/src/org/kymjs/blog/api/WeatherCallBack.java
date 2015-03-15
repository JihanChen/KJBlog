package org.kymjs.blog.api;

import org.kymjs.blog.bean.WeatherForecast;
import org.kymjs.blog.bean.WeatherToday;

/**
 * Created by lody  on 2015/3/15.
 */
public class WeatherCallBack {
    public static interface TodayWeatherCallBack{
        void onTodayWeatherSearchSuccess(WeatherToday weatherToday);
        void onfailture(String reason);
    }

    public static interface WeatherrForecastCallBack{
        void onWeatherrForecastSearchSuccess(WeatherForecast forecast);
        void onfailture(String reason);
    }
}

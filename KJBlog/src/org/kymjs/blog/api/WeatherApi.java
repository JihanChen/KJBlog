package org.kymjs.blog.api;

import org.kymjs.blog.bean.WeatherForecast;
import org.kymjs.blog.bean.WeatherToday;
import org.kymjs.blog.utils.JsonParser;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.utils.KJLoger;

import de.greenrobot.event.EventBus;

/**
 * Created by lody  on 2015/3/14.
 */
public class WeatherApi {
    private static final String TAG = WeatherApi.class.getSimpleName();
    private static final String WEATHER_TODAY_SUFFIX_URL = "/data/2.5/weather";
    private static final String WEATHER_FORECAST_SUFFIX_URL = "/data/2.5/forecast/daily";
    private static final String APPID = "89516341f251e02d934e044d09d5001d";
    public static String base_url = "http://api.openweathermap.org";

    public static void getTodayWeather(final String city,String lang){

        HttpConfig config = new HttpConfig();
        config.maxRetries = 4;// 出错重连次数

        HttpParams params = new HttpParams();
        params.put("q",city);
        params.put("appid",APPID);
        params.put("lang",lang);
        params.put("units","metric");

        final KJHttp http = new KJHttp(config);

        http.get(getAbsoluteUrl(WEATHER_TODAY_SUFFIX_URL), params, new HttpCallBack() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }

            @Override
            public void onSuccess(int statusCode, String content) {
                KJLoger.debug(content);
                WeatherToday today = JsonParser.fromJsonObject(content, WeatherToday.class);
                sendTodayWeatherSearchFinishEvent(today);
            }
        });
    }

    public static void getWeatherForecast(String city,String lang,int cnt){

        HttpConfig config = new HttpConfig();
        config.maxRetries = 4;// 出错重连次数

        HttpParams params = new HttpParams();
        params.put("q",city);
        params.put("appid",APPID);
        params.put("lang",lang);
        params.put("units","metric");
        params.put("cnt",String.valueOf(cnt));

        final KJHttp http = new KJHttp(config);

        http.get(getAbsoluteUrl(WEATHER_FORECAST_SUFFIX_URL), params, new HttpCallBack() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }

            @Override
            public void onSuccess(int statusCode, String content) {
                KJLoger.debug(content);
                WeatherForecast forecast = JsonParser.fromJsonObject(content, WeatherForecast.class);
                sendWeatherForecastSearchFinishEvent(forecast);

            }
        });
    }

    /**
     * 发送今日天气查询完成的事件
     *
     * @param today 天气
     */
    private static void sendTodayWeatherSearchFinishEvent(WeatherToday today) {


        EventBus.getDefault().post(today);

    }

    /**
     * 发送天气预报查询完成的事件
     *
     * @param forecast
     */
    private static void sendWeatherForecastSearchFinishEvent(WeatherForecast forecast) {


        EventBus.getDefault().post(forecast);

    }

    /**
     *
     * @param relativeUrl
     * @return 完整http路径
     */
    private static String getAbsoluteUrl(String relativeUrl) {
        if(relativeUrl.startsWith("http")){
            return relativeUrl;
        }
        String url = base_url + relativeUrl;
        KJLoger.debug(url);
        return url;
    }

}

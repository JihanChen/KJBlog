package org.kymjs.blog.api;

import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kymjs.blog.bean.Place;
import org.kymjs.blog.domain.SearchCityEvent;
import org.kymjs.blog.utils.JsonParser;
import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpConfig;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.utils.KJLoger;

import java.lang.reflect.Type;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by lody  on 2015/3/13.
 * 单元测试:PASS
 *
 * 城市搜索功能类
 * <p/>
 * 使用雅虎的YQL进行数据筛选
 */
public class CityApi {
    protected static final String TAG = CityApi.class.getSimpleName();
    /** URL */
    private static final String SEARCH_CITY_URL = "http://query.yahooapis.com/v1/public/yql";
    /** YQL语句 */
    private static final String YQL_GEO_PLACES = "select * from geo.places where text=";

    public static void searchCity(String city) {
        final HttpParams params = new HttpParams();
        HttpConfig config = new HttpConfig();
        params.put("format", "json");//得到的数据为JSON
        params.put("q", YQL_GEO_PLACES + "\"" + city.trim() + "\"");

        config.maxRetries = 4;// 出错重连次数

        final KJHttp http = new KJHttp(config);


        //使用Post方式发送Http请求
        http.post(SEARCH_CITY_URL, params, new HttpCallBack() {

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);

            }

            @Override
            public void onSuccess(int code, String content) {
                super.onSuccess(code, content);
                KJLoger.debug(content);

                try {
                    //解析JSON
                    JSONArray jsonPlace = new JSONObject(content).optJSONObject("query").optJSONObject("results").optJSONArray("place");
                    final Type type = new TypeToken<List<Place>>() {
                    }.getType();
                    List<Place> places = JsonParser.fromJsonArray(jsonPlace.toString(), type);

                    sendPlaceSearchFinishEvent(places);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 发送搜索完成的事件
     *
     * @param places
     */
    private static void sendPlaceSearchFinishEvent(List<Place> places) {

        KJLoger.debug("Country Size = " + places.size());
        EventBus.getDefault().post(new SearchCityEvent((places)));

    }
}

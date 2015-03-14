package org.kymjs.blog.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.kymjs.blog.R;
import org.kymjs.blog.api.WeatherApi;
import org.kymjs.blog.bean.Place;
import org.kymjs.blog.bean.WeatherForecast;
import org.kymjs.blog.bean.WeatherToday;
import org.kymjs.kjframe.ui.BindView;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 *
 * Created by lody  on 2015/3/14.
 *
 * Plugin 1：天气界面
 *
 * State;未完成
 */
public class WeatherActivity extends TitleBarActivity {

    @BindView(id = R.id.search_city,click = true)
    ImageView search_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(WeatherActivity.this);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.frag_weather);

        WeatherApi.getWeatherForecast( "NingBo", "zh_cn", 14);
    }


    public void onEventMainThread(List<Place> places){
       //TODO:Call Back places by others

    }
    public void onEventMainThread(WeatherToday weatherToday){
       //TODO:Call Back places by others
    }

    public void onEventMainThread(WeatherForecast weatherForecast){
      //TODO:Call Back places by others

    }

    @Override
    protected void onMenuClick() {
        super.onMenuClick();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.search_city:
                //DO it!
                break;


        }
    }
}

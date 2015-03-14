package org.kymjs.blog.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.kymjs.blog.R;
import org.kymjs.blog.bean.Place;
import org.kymjs.blog.bean.WeatherForecast;
import org.kymjs.blog.bean.WeatherToday;

import java.util.List;

/**
 * Created by lody on 2015/3/13.
 */
public class WeatherFragment extends TitleBarFragment {

    // TODO:天气插件
    // STATE:未完成

    //@BindView(id = R.id.search_city,click = true)
    ImageView search_city;

    public static final String TAG = WeatherFragment.class.getSimpleName();

    private Activity aty;

    @Override
    protected View inflaterView(LayoutInflater layoutInflater,
            ViewGroup viewGroup, Bundle bundle) {

        aty = getActivity();
        View root = View.inflate(aty, R.layout.frag_weather, null);
        return root;
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
    protected void widgetClick(View v) {
        super.widgetClick(v);

    }
}

package org.kymjs.blog.ui.fragment;

import java.util.List;

import org.kymjs.blog.R;
import org.kymjs.blog.api.CityApi;
import org.kymjs.blog.api.CityCallBack;
import org.kymjs.blog.bean.Place;
import org.kymjs.kjframe.ui.ViewInject;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by lody on 2015/3/13.
 */
public class WeatherFragment extends TitleBarFragment implements CityCallBack {

    // TODO:天气插件
    // STATE:城市搜索初步完成，对话框还需美化
    // NOTICE:回调注意线程

    public static final String TAG = WeatherFragment.class.getSimpleName();

    private Activity aty;

    private View searchCityView;
    private EditText searchEdit;
    private Button searchButton;
    private ListView cityList;

    @Override
    protected View inflaterView(LayoutInflater layoutInflater,
            ViewGroup viewGroup, Bundle bundle) {

        aty = getActivity();
        View root = View.inflate(aty, R.layout.frag_weather, null);
        View searchCityView = View.inflate(getActivity(),
                R.layout.frag_search_city, null);
        this.searchCityView = searchCityView;
        bindSearchView();

        return root;
    }

    private void bindSearchView() {
        searchEdit = (EditText) searchCityView
                .findViewById(R.id.search_city_edit);
        searchButton = (Button) searchCityView.findViewById(R.id.search_btn);
        searchButton.setOnClickListener(this);
        cityList = (ListView) searchCityView.findViewById(R.id.list_city);
    }

    /**
     * 将搜索到的城市填充到ListView
     */
    private void fillListView(List<Place> places) {
        cityList.setVisibility(View.VISIBLE);
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        actionBarRes.title = "爱看天气";
        actionBarRes.menuImageId = R.drawable.ic_search;
        actionBarRes.backImageId = R.drawable.titlebar_back;
    }

    private void showSearchWindow() {
        new AlertDialog.Builder(getActivity()).setView(searchCityView).show();
    }

    @Override
    public void onMenuClick() {
        super.onMenuClick();
        showSearchWindow();
    }

    @Override
    public void onBackClick() {
        super.onBackClick();
        aty.finish();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.search_btn:
            applySearch();
            break;
        }
    }

    /**
     * 点击搜索城市的按钮
     */
    private void applySearch() {
        final String content = searchEdit.getText().toString().trim();
        if (content.isEmpty())
            return;
        performSearchCity(content);
    }

    /**
     * 开始搜索
     */
    private void performSearchCity(String city) {
        ViewInject.toast("搜索中...");
        CityApi.searchCity(city, this);

    }

    @Override
    public void onCitySearchSuccess(final List<Place> places) {
        outsideAty.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ViewInject.toast("搜索到" + places.size() + "个城市。");
                ViewInject.toast(places.get(0).name);

                if (places.size() == 0) {
                    return;
                }
                fillListView(places);
            }
        });

    }

    @Override
    public void onCitySearchFailture(String reason) {
        // 无视之
    }
}

package org.kymjs.blog.api;

import org.kymjs.blog.R;

/**
 * Created by lody  on 2015/3/14.
 */

public class WeatherIconApi {

    /**
     * @param icon
     * @param id
     * @return 天气资源ID
     */
    public static int getWeatherIcon(String icon,int id){
        if(icon.endsWith("d")){
            if(id == 800 || id == 903){
                return R.drawable.wic_clear_d;
            }else if(id >= 801 && id<=803){
                return R.drawable.wic_cloudy_d;
            }else if(id == 804){
                return R.drawable.wic_big_cloudy;
            }else if(id >= 200 && id<300){
                return R.drawable.wic_thunder;
            }else if(id == 300 || id == 310 ||
                    id == 312 ||id == 500 || id == 501){
                return R.drawable.wic_drizzle_d;
            }else if(id == 301 || id == 311 || id == 321){
                return R.drawable.wic_drizzle;
            }else if(id == 503 || id == 504 || id == 521){
                return R.drawable.wic_big_rain;
            }else if(id == 511 || id == 621){
                return R.drawable.wic_sleet;
            }else if(id == 502 || id == 520 || id == 522){
                return R.drawable.wic_big_rain_d;
            }else if(id == 600 || id == 601 ||
                    id == 602 || id == 904){
                return R.drawable.wic_snow_d;
            }else if(id == 611){
                return R.drawable.wic_rain_and_snow;
            }else if(id == 701){
                return R.drawable.wic_fog_mist;
            }else if(id == 711 || id == 731){
                return R.drawable.wic_smoke;
            }else if(id == 721){
                return R.drawable.wic_haze_d;
            }else if(id>=900 && id<902){
                return R.drawable.wic_storm;
            }else if(id == 905){
                return R.drawable.wic_storm;
            }else if(id == 906){
                return R.drawable.wic_hail;
            }

        }else{
            if(id == 800 || id == 903){
                return R.drawable.wic_clear_n;
            }else if(id >= 801 && id<=803){
                return R.drawable.wic_cloudy_n;
            }else if(id == 804){
                return R.drawable.wic_big_cloudy;
            }else if(id >= 200 && id<300){
                return R.drawable.wic_thunder;
            }else if(id == 300 || id == 310 ||
                    id == 312 ||id == 500 || id == 501){
                return R.drawable.wic_drizzle_n;
            }else if(id == 301 || id == 311 || id == 321){
                return R.drawable.wic_drizzle;
            }else if(id == 503 || id == 504 || id == 521){
                return R.drawable.wic_big_rain;
            }else if(id == 511 || id == 621){
                return R.drawable.wic_sleet;
            }else if(id == 502 || id == 520 || id == 522){
                return R.drawable.wic_big_rain_n;
            }else if(id == 600 || id == 601 ||
                    id == 602 || id == 904){
                return R.drawable.wic_snow_n;
            }else if(id == 611){
                return R.drawable.wic_rain_and_snow;
            }else if(id == 701){
                return R.drawable.wic_fog_mist;
            }else if(id == 711 || id == 731){
                return R.drawable.wic_smoke;
            }else if(id == 721){
                return R.drawable.wic_haze_n;
            }else if(id>=900 && id<902){
                return R.drawable.wic_storm;
            }else if(id == 905){
                return R.drawable.wic_storm;
            }else if(id == 906){
                return R.drawable.wic_hail;
            }
        }
        return R.drawable.wic_unknow;
    }

}

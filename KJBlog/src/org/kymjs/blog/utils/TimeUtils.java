package org.kymjs.blog.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.kymjs.kjframe.utils.StringUtils;

public class TimeUtils {

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static boolean dateIsTody(String sdate) {
        Date time = null;

        if (StringUtils.isInEasternEightZones()) {
            time = StringUtils.toDate(sdate);
        } else {
            time = StringUtils.transformTime(StringUtils.toDate(sdate),
                    TimeZone.getTimeZone("GMT+08"), TimeZone.getDefault());
        }
        if (time == null) {
            return false;
        }
        String ftime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = dateFormater.get().format(cal.getTime());
        String paramDate = dateFormater.get().format(time);
        return curDate.equals(paramDate);
    }
}

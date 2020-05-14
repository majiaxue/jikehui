package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyTimeUtil {
    /**
     * 时间戳转时间
     *
     * @param time 1541569323
     * @return 2018-11-07
     */
    public static String date2String(String time) {
        if (time != null) {
            Long value = Long.valueOf(time);
            Date date = new Date(value);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            return format.format(date);
        } else {
            return "";
        }
    }

    /**
     * 时间戳转时间
     *
     * @param time 1541569323155
     * @return 2018-11-07 13:42:03
     */
    public static String date2StringLong(String time) {
        if (time != null) {
            Long value = Long.valueOf(time);
            Date date = new Date(value);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            return format.format(date);
        } else {
            return "";
        }

    }
}

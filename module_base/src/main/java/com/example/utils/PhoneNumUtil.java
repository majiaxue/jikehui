package com.example.utils;

import android.text.TextUtils;

public class PhoneNumUtil {
    public static boolean isMobileNO(String mobiles) {
        String telRegex = "13\\d{9}|14\\d{9}|15\\d{9}|18\\d{9}|17\\d{9}|16\\d{9}|19\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
}

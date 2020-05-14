package com.example.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;


public class LogUtil {
    private static boolean isDebug;

    public static void e(String message) {
//        if (isDebug) {
            while (message.length() >= 2000) {
                Log.e("tag", message.substring(0, 2000));
                message = message.substring(2000);
            }
            Log.e("tag", message);
//        }
    }

    public static boolean isDebug(Context context) {
        isDebug = context.getApplicationInfo() != null &&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        context = null;
        return isDebug;
    }
}

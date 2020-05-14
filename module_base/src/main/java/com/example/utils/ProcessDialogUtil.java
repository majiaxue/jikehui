package com.example.utils;

import android.app.Activity;
import android.content.Context;

public class ProcessDialogUtil {

    private static CustomDialog customDialog;

    /**
     * 显示进度对话框，用于串行请求
     *
     * @param context
     */
    public static void showProcessDialog(final Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (null != activity && !activity.isFinishing()) {
                if (customDialog == null) {
                    customDialog = new CustomDialog(activity);
                }
                if (!customDialog.isShowing()) {
                    customDialog.show();
                    customDialog.setOwnerActivity(activity);
                }
            }
        }

    }

    /**
     * 显示进度对话框，用于并请求
     *
     * @param context
     */
//    public static void showProcessDialog(final Context context) {
//        if (context instanceof Activity) {
//            Activity activity = (Activity) context;
//            if (null != activity && !activity.isFinishing()) {
//                if (customDialog == null) {
//                    customDialog = new CustomDialog(context);
//                }
//                if (!customDialog.isShowing()) {
//                    customDialog.show();
//                    customDialog.setOwnerActivity(activity);
//                }
//            }
//        }
//    }

    /**
     * 是否正在显示
     *
     * @return
     */
    public static boolean isShowing() {
        if (customDialog != null) {
            return customDialog.isShowing();
        }
        return false;
    }

    /**
     * 关闭对话框
     */
    public static synchronized void dismissDialog() {

        if (customDialog != null && customDialog.isShowing()) {
            Activity activity = customDialog.getOwnerActivity();
            if (null != activity && !activity.isFinishing()) {
                try {
                    Thread.sleep(500);
                    customDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        customDialog = null;
    }
}

package com.example.utils;

import android.app.Activity;

import java.util.Stack;

public class AppManager {
    private static Stack<Activity> activityStack;
    private static Stack<Activity> goodsActivity;
    private static AppManager instance;

    private AppManager() {
    }

    /**
     * 单例模式创建实例
     *
     * @return
     */
    public static AppManager getInstance() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加activity到堆栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public void addGoodsActivity(Activity activity) {
        if (goodsActivity == null) {
            goodsActivity = new Stack<Activity>();
        }
        goodsActivity.add(activity);
    }

    public void finishGoodsActivity() {
        for (Activity activity : goodsActivity) {
            if (activity != null)
                activity.finish();
        }
        goodsActivity.clear();
    }

    /**
     * 获取当前activity(堆栈中最后一个压入的)
     */
    public Activity getCurrentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定activity
     *
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束所有activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityStack) {
            if (activity != null)
                activity.finish();
        }
        activityStack.clear();
    }

    /**
     * 退出程序
     */
    public void AppExit() {
        finishAllActivity();
        System.exit(0);
    }
}

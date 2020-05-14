package com.example.utils;

import android.content.Context;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class JpushUtil {
    private static Context mContext;

    public static void getInstance(Context context) {
        mContext = context;
    }

    public static void setAlias(String alias) {
        JPushInterface.setAliasAndTags(mContext, alias, null, new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {
                LogUtil.e("别名回调：" + s);
            }
        });
    }

    public static void deleteAlias() {
        JPushInterface.deleteAlias(mContext, 1);
    }

    public static void setTags() {
        Set<String> set = new HashSet<String>();
        set.add("boss");
        JPushInterface.setTags(mContext, 2, set);
    }

    public static void deleteTags() {
        Set<String> set = new HashSet<String>();
        set.add("boss");
        JPushInterface.deleteTags(mContext, 2, set);
    }
}

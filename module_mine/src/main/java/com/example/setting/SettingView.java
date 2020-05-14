package com.example.setting;

import android.content.Intent;
import android.graphics.Bitmap;

import com.example.bean.UserInfoBean;
import com.example.mvp.IView;

public interface SettingView extends IView {
    void takePhoto(Intent intent);

    void photoAlbum(Intent intent);

    void cropPhoto(Intent intent);

    void showHeader(String url);

    void clearSuccess();

    void getDataSUccess(UserInfoBean userInfoBean);
}

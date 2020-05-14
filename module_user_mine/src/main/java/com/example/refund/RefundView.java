package com.example.refund;

import android.content.Intent;
import android.net.Uri;

import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public interface RefundView extends IView {
    void takePhoto(Intent intent);

    void photoAlbum(Intent intent);

    void selectPhoto(Uri uri);

    void showHeader(String base64);
}

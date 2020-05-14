package com.example.order_assess;

import android.content.Intent;
import android.net.Uri;

import com.example.mvp.IView;
import com.example.order_assess.adapter.OrderAssessAdapter;

import java.util.List;

public interface OrderAssessView extends IView {

    void loadRv(OrderAssessAdapter adapter);

    void takePhoto(Intent intent);

    void photoAlbum(Intent intent);

    void listImage(List<String> listImage);

    //    void imageUri(Uri uri);
    void imagePath(String path);

    void hideAdd();

    void showAdd();

    void showRv();

    void hideRv();
}

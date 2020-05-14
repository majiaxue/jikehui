package com.example.map_detail;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class MapDetailPresenter extends BasePresenter<MapDetailView> {
    public MapDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}

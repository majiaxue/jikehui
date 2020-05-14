package com.example.shou_quan;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class ShouQuanPresenter extends BasePresenter<ShouQuanView> {
    public ShouQuanPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}

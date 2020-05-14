package com.example.local_goods_detail;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class LocalGoodsDetailPresenter extends BasePresenter<LocalGoodsDetailView> {
    public LocalGoodsDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}

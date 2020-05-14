package com.example.flashsale;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class FlashSalePresenter extends BasePresenter<FlashSaleView> {

    public FlashSalePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}

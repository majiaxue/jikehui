package com.example.local_order_detail;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class LocalOrderDetailPresenter extends BasePresenter<LocalOrderDetailView> {
    public LocalOrderDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}

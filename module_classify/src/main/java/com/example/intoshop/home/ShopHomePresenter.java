package com.example.intoshop.home;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/5/21
 * Describe:
 */
public class ShopHomePresenter extends BasePresenter<ShopHomeView> {

    public ShopHomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}

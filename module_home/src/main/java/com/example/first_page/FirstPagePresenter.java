package com.example.first_page;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class FirstPagePresenter extends BasePresenter<FirstPageView> {
    public FirstPagePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}

package com.example.superbrand.handpick;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class HandPickPresenter extends BasePresenter<HandPickView> {

    public HandPickPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}

package com.example.help_detail;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class HelpDetailPresenter extends BasePresenter<HelpDetailView> {
    public HelpDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}

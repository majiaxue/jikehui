package com.example.message_detail;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class MessageDetailPresenter extends BasePresenter<MessageDetailView> {
    public MessageDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}

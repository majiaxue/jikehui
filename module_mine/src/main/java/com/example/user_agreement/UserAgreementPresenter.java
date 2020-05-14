package com.example.user_agreement;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class UserAgreementPresenter extends BasePresenter<UserAgreementView> {
    public UserAgreementPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

}

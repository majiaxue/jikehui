package com.example.shop_home;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.example.mvp.BasePresenter;
import com.example.utils.LogUtil;

public class TShopHomePresenter extends BasePresenter<TShopHomeView> {
    public TShopHomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void login() {

        final AlibcLogin alibcLogin = AlibcLogin.getInstance();

        alibcLogin.showLogin(new AlibcLoginCallback() {

            @Override
            public void onSuccess(int loginResult, String openId, String userNick) {

                LogUtil.e("获取淘宝用户信息: " + AlibcLogin.getInstance().getSession());

            }

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(mContext, "登录失败 ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}

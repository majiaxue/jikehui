package com.example.utils;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;
import com.example.common.CommonResource;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class TBUtil {

    public void login(final Context context, final OnSuccessListener listener) {

        final AlibcLogin alibcLogin = AlibcLogin.getInstance();

        alibcLogin.showLogin(new AlibcLoginCallback() {

            @Override
            public void onSuccess(int loginResult, String openId, String userNick) {
                listener.onSuccess();
            }

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(context, "登录失败 ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void shouQuan() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHeadWithout(CommonResource.SHOUQUAN, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("授权：" + result);
                SPUtil.addParm("link", result);
                ARouter.getInstance().build("/module_classify/shouquan").withString("url", result.replace("web", "wap")).navigation();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("授权：" + errorMsg);
            }
        }));
    }
}

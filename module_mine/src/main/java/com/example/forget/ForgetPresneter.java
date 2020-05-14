package com.example.forget;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.bean.UserInfoBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.MapUtil;
import com.example.utils.PhoneNumUtil;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class ForgetPresneter extends BasePresenter<ForgetView> {
    public ForgetPresneter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void commit(String phone, String code, String password, String confirmPassword) {
        if ("".equals(phone) || "".equals(code) || "".equals(password) || "".equals(confirmPassword)) {
            Toast.makeText(mContext, mContext.getResources().getString(com.example.module_base.R.string.please_input_more_info), Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(mContext, mContext.getResources().getString(com.example.module_base.R.string.password_no_same), Toast.LENGTH_SHORT).show();
        } else if (!PhoneNumUtil.isMobileNO(phone)) {
            Toast.makeText(mContext, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
        } else {
            UserInfoBean userInfoBean = new UserInfoBean();
            userInfoBean.setPhone(phone);
            userInfoBean.setCheckCode(code);
            userInfoBean.setPassword(password);
            userInfoBean.setNewPassword(confirmPassword);
            String jsonString = JSON.toJSONString(userInfoBean);
            Map map = MapUtil.getInstance().addParms("memberStr", jsonString).build();

            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postData(CommonResource.FORGET, map);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    ((Activity) mContext).finish();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
                }
            }));
        }
    }

    public void getCodeNum(String phone) {
        if (PhoneNumUtil.isMobileNO(phone)) {
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.LOGIN_PHONE + "/" + phone);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    getView().getCodeSuccess();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {

                }
            }));
        } else {
            Toast.makeText(mContext, "请填写正确的手机号", Toast.LENGTH_SHORT).show();
        }
    }
}

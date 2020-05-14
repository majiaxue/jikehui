package com.example.replace_phone;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.bean.UserInfoBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.PhoneNumUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class ReplacePhonePresenter extends BasePresenter<ReplacePhoneView> {
    public ReplacePhonePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getCodeNum(String string) {
        if (PhoneNumUtil.isMobileNO(string)) {
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.GETREGISTERCODE + "/" + string);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    getView().getCodeSuccess();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
                }
            }));
        } else {
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        }
    }

    public void commit(String oldNum, String newNum, String code, UserInfoBean userInfoBean) {
        if ("".equals(oldNum) || "".equals(newNum) || "".equals(code)) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.please_input_more_info), Toast.LENGTH_SHORT).show();
        } else if (!PhoneNumUtil.isMobileNO(oldNum) || !PhoneNumUtil.isMobileNO(newNum)) {
            Toast.makeText(mContext, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
        } else {
            userInfoBean.setOldPhone(oldNum);
            userInfoBean.setPhone(newNum);
            userInfoBean.setCheckCode(code);
            String jsonString = JSON.toJSONString(userInfoBean);
            Map map = MapUtil.getInstance().addParms("memberStr", jsonString).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).putData(CommonResource.REVISEPHONE, map, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("修改手机号：" + result);
                    Toast.makeText(mContext, "修改成功", Toast.LENGTH_SHORT).show();
                    ((Activity) mContext).finish();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "-------" + errorMsg);
                    Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
                }
            }));
        }
    }
}

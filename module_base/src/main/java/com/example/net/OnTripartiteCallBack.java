package com.example.net;

import com.example.common.CommonResource;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.kongzue.dialog.v3.WaitDialog;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/6/6
 * Describe:
 */
public class OnTripartiteCallBack extends DisposableObserver<ResponseBody> {

    private OnDataListener listener;


    /**
     * @param listener 回调监听
     */
    public OnTripartiteCallBack(OnDataListener listener) {
        this.listener = listener;
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        ProcessDialogUtil.dismissDialog();

//        WaitDialog.dismiss();
        try {
            String string = responseBody.string();
            if (string.indexOf("error_response") != -1) {
                //包含error_response
                listener.onError("失败", string);
            } else if (string.indexOf("\"error\":6001") != -1) {
                //包含error
                listener.onError("失败", string);
            } else if (string.indexOf("\"code\":-1") != -1) {
                //包含error
                listener.onError("失败", string);
            } else {
                //不包含
                listener.onSuccess(string, "成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        ProcessDialogUtil.dismissDialog();

//        WaitDialog.dismiss();
        try {
            if (e instanceof SocketTimeoutException) {//请求超时
            } else if (e instanceof ConnectException) {//网络连接超时
                listener.onError(CommonResource.ERROR, "网络连接超时");
            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                listener.onError(CommonResource.ERROR, "安全证书异常");
            } else {
                listener.onError(CommonResource.ERROR, "error");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            LogUtil.e("onError---->" + e.getMessage());
        }
    }

    @Override
    public void onComplete() {
//        ProcessDialogUtil.dismissDialog();
    }
}

package com.example.local_payment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.example.bean.AliPayBean;
import com.example.bean.LocalOrderBean;
import com.example.bean.WeChatPayBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.PopUtil;
import com.example.utils.SPUtil;
import com.example.view.SelfDialog;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LocalPaymentPresenter extends BasePresenter<LocalPaymentView> {
    private final int ALI_CODE = 0x123;
    private String info = "";

    public LocalPaymentPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == ALI_CODE) {
                Map<String, String> map = (Map<String, String>) msg.obj;
                String resultStatus = map.get("resultStatus");
                String result = map.get("result");
                String memo = map.get("memo");
                if ("9000".equals(resultStatus)) {
                    SPUtil.addParm("wxpay", "13");
                    ((Activity) mContext).finish();
                    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }

    };

    public void pay(boolean isWeChat, LocalOrderBean bean) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalAmount", bean.getTotalMoney());
        jsonObject.put("productName", CommonResource.PROJECTNAME);
        jsonObject.put("orderFlag", true);
        jsonObject.put("orderSn", bean.getOrderSn());
        jsonObject.put("redPackedId", bean.getRedPackedId() == null ? "" : bean.getRedPackedId());
        jsonObject.put("redPackedMoney", bean.getRedPackedMoney() == null ? "0" : bean.getRedPackedMoney());
        jsonObject.put("userCode", SPUtil.getUserCode());
        if (isWeChat) {
            wxpay(jsonObject);
        } else {
            alipay(jsonObject);
        }
    }

    private void wxpay(JSONObject jsonObject) {
        final IWXAPI api = WXAPIFactory.createWXAPI(mContext, CommonResource.WXAPPID, false);
        String jsonString = JSON.toJSONString(jsonObject);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_WX_PAY, requestBody);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                getView().callBack();
                LogUtil.e("微信支付：" + result);
                SPUtil.addParm("wxpay", "12");
                WeChatPayBean payBean = JSON.parseObject(result, WeChatPayBean.class);

                PayReq request = new PayReq();
                request.appId = payBean.getAppid();
                request.partnerId = payBean.getPartnerid();
                request.prepayId = payBean.getPrepayid();
                request.packageValue = "Sign=WXPay";
                request.nonceStr = payBean.getNoncestr();
                request.timeStamp = payBean.getTimestamp();
                request.sign = payBean.getSign();

                api.sendReq(request);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                getView().callBack();
                LogUtil.e(errorCode + "-----------" + errorMsg);
            }
        }));
    }

    private void alipay(JSONObject jsonObject) {
        String jsonString = JSON.toJSONString(jsonObject);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_ALI_PAY, requestBody);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                getView().callBack();
                LogUtil.e("支付宝支付：" + result);
                AliPayBean aliPayBean = JSON.parseObject(result, AliPayBean.class);
                info = aliPayBean.getBody();
                Thread thread = new Thread(payRunnable);
                thread.start();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                getView().callBack();
                LogUtil.e(errorCode + "------------" + errorMsg);
            }
        }));
    }

    private Runnable payRunnable = new Runnable() {

        @Override
        public void run() {
            PayTask alipay = new PayTask((Activity) mContext);
            Map<String, String> result = alipay.payV2(info, true);

            Message msg = new Message();
            msg.what = ALI_CODE;
            msg.obj = result;
            mHandler.sendMessage(msg);
        }
    };

    public void goBack() {
        final SelfDialog dialog = new SelfDialog(mContext);
        dialog.setTitle("提示");
        dialog.setMessage("确定要离开吗？");
        dialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                dialog.dismiss();
            }
        });

        dialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                dialog.dismiss();
                ((Activity) mContext).finish();
            }
        });

        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                PopUtil.setTransparency(mContext, 1.0f);
            }
        });
        PopUtil.setTransparency(mContext, 0.3f);
    }
}

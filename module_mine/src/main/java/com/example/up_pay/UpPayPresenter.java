package com.example.up_pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.example.bean.WeChatPayBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.view.SelfDialog;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import io.reactivex.Observable;

public class UpPayPresenter extends BasePresenter<UpPayView> {
    private final int ALI_CODE = 0x123;
    private String orderSn;
    private String type;
    private String info;
    private Intent intent;

    public UpPayPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(mContext);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == ALI_CODE) {
                Map<String, String> map = (Map<String, String>) msg.obj;
                String resultStatus = map.get("resultStatus");

                if ("9000".equals(resultStatus)) {
                    intent.putExtra("type", "1");
                    ((Activity) mContext).setResult(Activity.RESULT_OK, intent);
                    ((Activity) mContext).finish();
                    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }

    };

    public void pay(boolean isWeChat, String money, String type, String name, String levelId) {
        this.type = type;
        if (isWeChat) {
            final IWXAPI api = WXAPIFactory.createWXAPI(mContext, CommonResource.WXAPPID, false);
            ProcessDialogUtil.showProcessDialog(mContext);
//            WaitDialog.show((AppCompatActivity)mContext,null);

            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postDataWithout(CommonResource.LIBAO_WXPAY + "?userCode=" + SPUtil.getUserCode() + "&totalAmount=" + money + "&levelId=" + levelId + "&productName=" + CommonResource.PROJECTNAME + "-" + name);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("微信支付-------------->" + result);

//                    ProcessDialogUtil.dismissDialog();
                    try {
                        WeChatPayBean payBean = JSON.parseObject(result, WeChatPayBean.class);

                        PayReq request = new PayReq();
                        request.appId = payBean.getAppid();
                        request.partnerId = payBean.getPartnerid();
                        request.prepayId = payBean.getPrepayid();
                        request.packageValue = "Sign=WXPay";
                        request.nonceStr = payBean.getNoncestr();
                        request.timeStamp = payBean.getTimestamp();
                        request.sign = payBean.getSign();
                        orderSn = payBean.getLevelOrderSn();

                        api.registerApp(CommonResource.WXAPPID);
                        api.sendReq(request);
                        SPUtil.addParm("wxpay", "4");
                        getView().callBack();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    getView().callBack();
                    LogUtil.e(errorCode + "------------" + errorMsg);
                }
            }));
        } else {
            ProcessDialogUtil.showProcessDialog(mContext);
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postDataWithout(CommonResource.LIBAO_ZFBPAY + "?userCode=" + SPUtil.getUserCode() + "&totalAmount=" + money + "&levelId=" + SPUtil.getStringValue(CommonResource.LEVELID));
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("付款：" + result);
                    try {
                        intent = new Intent();

                        Map parseObject = JSON.parseObject(result, Map.class);
                        info = (String) parseObject.get("body");
                        orderSn = (String) parseObject.get("msg");
                        Thread thread = new Thread(payRunnable);
                        thread.start();
                        getView().callBack();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    getView().callBack();
                }
            }));
        }
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
                PopUtils.setTransparency(mContext, 1.0f);
            }
        });
        PopUtils.setTransparency(mContext, 0.3f);
    }
}

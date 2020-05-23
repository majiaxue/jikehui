package com.example.up_order_confirm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.example.bean.OrderConfirmBean;
import com.example.bean.ShippingAddressBean;
import com.example.bean.WeChatPayBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
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
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class UpOrderConfirmPresenter extends BasePresenter<UpOrderConfirmView> {
    private final int ALI_CODE = 0x123;
    public ShippingAddressBean addressBean;
    private String orderSn;
    public boolean isCan = false;
    private String info;

    public UpOrderConfirmPresenter(Context context) {
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
                    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    ((Activity) mContext).finish();
                } else {
                    Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                    removeOrder();
                }
            }
        }

    };

    public void getAddress() {
        ProcessDialogUtil.showProcessDialog(mContext);
//        WaitDialog.show((AppCompatActivity)mContext,null);

        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.MOREN_ADDRESS, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("默认地址：" + result);
                addressBean = JSON.parseObject(result, ShippingAddressBean.class);
                if (getView() != null) {
                    isCan = true;
                    getView().loadAddress(addressBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("dizhi:" + errorCode + "-------" + errorMsg);
                if (getView() != null) {
                    getView().noAddress();
                }
            }
        }));

    }

    public void jumpToShippingAddress() {
        ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").withString("from", "order").navigation((Activity) mContext, 123);
    }

    public void commit(boolean isWeChat, OrderConfirmBean bean, String name, String levelId) {
        LogUtil.e("bean---------"+bean.toString());
        LogUtil.e("斤斤计较斤斤计较"+levelId);
        if (!isCan) {
            Toast.makeText(mContext, "未获取到收货地址，请重试", Toast.LENGTH_SHORT).show();
        } else {

            String jsonString = JSON.toJSONString(bean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);

            ProcessDialogUtil.showProcessDialog(mContext);

            if (isWeChat) {
                final IWXAPI api = WXAPIFactory.createWXAPI(mContext, CommonResource.WXAPPID, false);

                Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithBody(CommonResource.LIBAO_WXPAY + "?totalAmount=" + bean.getPrice() + "&userCode=" + SPUtil.getUserCode() + "&levelId=" + levelId + "&productName=" + CommonResource.PROJECTNAME + "-" + name + "&type=1", requestBody, SPUtil.getToken());
                RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
                    @Override
                    public void onSuccess(String result, String msg) {
                        LogUtil.e("微信支付-------------->" + result);
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
                            SPUtil.addParm("wxpay", "2");
                            getView().callBack();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(String errorCode, String errorMsg) {
                        getView().callBack();
//                        ProcessDialogUtil.dismissDialog();
                        LogUtil.e(errorCode + "------------" + errorMsg);
                    }
                }));
            } else {
                Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithBody(CommonResource.LIBAO_ZFBPAY + "?userCode=" + SPUtil.getUserCode() + "&totalAmount=" + bean.getPrice() + "&levelId=" + levelId + "&type=1", requestBody, SPUtil.getToken());
                RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                    @Override
                    public void onSuccess(String result, String msg) {
//                        ProcessDialogUtil.dismissDialog();
                        LogUtil.e("付款：" + result);
                        try {

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
//                        ProcessDialogUtil.dismissDialog();
                    }
                }));
            }
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

    public void removeOrder() {
        Map map = MapUtil.getInstance().addParms("orderSn", this.orderSn).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).getData(CommonResource.LIBAO_CANCEL_ORDER, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("取消订单：" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------" + errorMsg);
            }
        }));
    }
}

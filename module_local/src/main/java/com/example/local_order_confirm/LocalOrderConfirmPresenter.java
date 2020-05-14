package com.example.local_order_confirm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alipay.sdk.app.PayTask;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.AliPayBean;
import com.example.bean.BaseEntity;
import com.example.bean.LocalGetOrderSnBean;
import com.example.bean.LocalOrderBean;
import com.example.bean.RedPackageBean;
import com.example.bean.ShippingAddressBean;
import com.example.bean.WeChatPayBean;
import com.example.common.CommonResource;
import com.example.adapter.CouponWalletAdapter;
import com.example.local_order_confirm.adapter.LocalOrderConfirmAdapter;
import com.example.module_local.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnChangeHeaderListener;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class LocalOrderConfirmPresenter extends BasePresenter<LocalOrderConfirmView> {

    private ShippingAddressBean addressBean;
    private final int ALI_CODE = 0x123;
    private String info = "";
    private List<RedPackageBean> redPackageBeans;
    private RedPackageBean chooseRedPacgage;

    public LocalOrderConfirmPresenter(Context context) {
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
                    ((Activity) mContext).finish();
                    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }

    };

    public void chooseAddress() {
        ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").withString("from", "order").navigation((Activity) mContext, 456);
    }

    public void getAddress() {
        ProcessDialogUtil.showProcessDialog(mContext);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.MOREN_ADDRESS, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("默认地址：" + result);
                addressBean = JSON.parseObject(result, ShippingAddressBean.class);
                if (getView() != null) {
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

    public void initRv(List<LocalOrderBean.LocalOrderItemListBean> localOrderItemList) {
        LocalOrderConfirmAdapter orderConfirmAdapter = new LocalOrderConfirmAdapter(mContext, localOrderItemList, R.layout.rv_local_order_confirm);
        if (getView() != null) {
            getView().loadRv(orderConfirmAdapter);
        }
    }

    public void toPay(final boolean isWechat, LocalOrderBean bean) {
        if (TextUtils.isEmpty(bean.getOrderSn())) {
            String jsonString = JSON.toJSONString(bean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);

            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_SUBMIT_ORDER, requestBody);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("下单：" + result);
                    try {
                        BaseEntity baseEntity = JSON.parseObject(result, new TypeReference<BaseEntity<LocalGetOrderSnBean>>() {
                        }.getType());
                        LocalGetOrderSnBean localGetOrderSnBean = (LocalGetOrderSnBean) baseEntity.getData();
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("totalAmount", localGetOrderSnBean.getTotalMoney());
                        jsonObject.put("productName", CommonResource.PROJECTNAME);
                        jsonObject.put("orderFlag", true);
                        jsonObject.put("orderSn", localGetOrderSnBean.getOrderSn());
                        jsonObject.put("redPackedId", chooseRedPacgage == null ? "" : chooseRedPacgage.getId());
                        jsonObject.put("redPackedMoney", chooseRedPacgage == null ? "0" : chooseRedPacgage.getMoney());
                        jsonObject.put("userCode", SPUtil.getUserCode());
//                        Map map = MapUtil.getInstance()
//                                .addParms("totalAmount", localGetOrderSnBean.getTotalMoney())
//                                .addParms("productName", CommonResource.PROJECTNAME)
//                                .addParms("orderFlag", true)
//                                .addParms("orderSn", localGetOrderSnBean.getOrderSn())
//                                .addParms("redPackedId", chooseRedPacgage == null ? "" : chooseRedPacgage.getId())
//                                .addParms("redPackedMoney", chooseRedPacgage == null ? "0" : chooseRedPacgage.getMoney())
//                                .addParms("userCode", SPUtil.getUserCode())
//                                .build();

                        if (isWechat) {
                            wxpay(jsonObject);
                        } else {
                            alipay(jsonObject);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (getView() != null) {
                        getView().loadFinish();
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "-------------" + errorMsg);
                    if (getView() != null) {
                        getView().loadFinish();
                    }
                }
            }));
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("totalAmount", bean.getTotalMoney());
            jsonObject.put("productName", CommonResource.PROJECTNAME);
            jsonObject.put("orderFlag", true);
            jsonObject.put("orderSn", bean.getOrderSn());
            jsonObject.put("redPackedId", chooseRedPacgage == null ? "" : chooseRedPacgage.getId());
            jsonObject.put("redPackedMoney", chooseRedPacgage == null ? "0" : chooseRedPacgage.getMoney());
            jsonObject.put("userCode", SPUtil.getUserCode());
            if (isWechat) {
                wxpay(jsonObject);
            } else {
                alipay(jsonObject);
            }
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
                LogUtil.e("微信支付：" + result);
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
                LogUtil.e("支付宝支付：" + result);
                AliPayBean aliPayBean = JSON.parseObject(result, AliPayBean.class);
                info = aliPayBean.getBody();
                Thread thread = new Thread(payRunnable);
                thread.start();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
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

    public void getCouponList() {
        Map map = MapUtil.getInstance().addParms("status", "0").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getHead(CommonResource.LOCAL_COUPON_LIST, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("可用红包：" + result);
                redPackageBeans = JSON.parseArray(result, RedPackageBean.class);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------" + errorMsg);
            }
        }));
    }

    public void couponPop() {
        if (redPackageBeans != null && redPackageBeans.size() > 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.pop_local_coupon, null);
            RecyclerView rv = view.findViewById(R.id.pop_local_coupon_rv);

            final PopupWindow popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            popupWindow.setOutsideTouchable(true);
            popupWindow.setAnimationStyle(com.example.user_store.R.style.pop_bottom_anim);
            popupWindow.showAtLocation(new View(mContext), Gravity.BOTTOM, 0, 0);

            PopUtils.setTransparency(mContext, 0.3f);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    PopUtils.setTransparency(mContext, 1.0f);
                }
            });

            CouponWalletAdapter walletAdapter = new CouponWalletAdapter(mContext, redPackageBeans, R.layout.rv_coupon_wallet);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
            rv.setLayoutManager(linearLayoutManager);
            rv.setAdapter(walletAdapter);

            walletAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerView parent, View view, int position) {
                    chooseRedPacgage = redPackageBeans.get(position);
                    popupWindow.dismiss();
                    getView().loadCoupon(chooseRedPacgage);
                }
            });
        } else {
            Toast.makeText(mContext, "无可用优惠券", Toast.LENGTH_SHORT).show();
        }
    }

    public void songTypePop() {
        PopUtils.changeHeader(mContext, new OnChangeHeaderListener() {
            @Override
            public void setOnChangeHeader(final PopupWindow pop, TextView camera, TextView album) {
                camera.setText("商家配送");
                album.setText("到店自提");
                camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getView().loadSongType("商家配送");
                        pop.dismiss();
                    }
                });

                album.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getView().loadSongType("到店自提");
                        pop.dismiss();
                    }
                });
            }
        });
    }
}

package com.example.local_pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.AliPayBean;
import com.example.bean.LocalShopBean;
import com.example.bean.UserCouponBean;
import com.example.bean.WeChatPayBean;
import com.example.common.CommonResource;
import com.example.goods_detail.adapter.PopLingQuanAdapter;
import com.example.local_assess.LocalAssessActivity;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnAdapterListener;
import com.example.utils.PopUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.view.SelfDialog;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class LocalPayPresenter extends BasePresenter<LocalPayView> {
    private LocalShopBean bean;
    private List<UserCouponBean> beanList;
    private UserCouponBean chooseCoupon;
    private boolean isCanUse = true;
    private String orderSn;
    private int flag = 0;
    private String info = "";
    private final int ALI_CODE = 0x123;

    public LocalPayPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == ALI_CODE) {
                Map<String, String> map = (Map<String, String>) msg.obj;
                String resultStatus = map.get("resultStatus");
                if ("9000".equals(resultStatus)) {
                    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    jumpToAssess();
                } else {
                    Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }

    };

    public void commit(final String edit, final int payType, final String sellerId) {
        try {

            if (Double.valueOf(edit) == 0) {
                Toast.makeText(mContext, "请输入正确的支付金额", Toast.LENGTH_SHORT).show();
                getView().callBack();
            } else {
                if (!isCanUse) {
                    final SelfDialog dialog = new SelfDialog(mContext);
                    dialog.setTitle("提示");
                    dialog.setMessage("支付金额不满足优惠券条件，确定直接支付吗？");
                    dialog.setYesOnclickListener("取消", new SelfDialog.onYesOnclickListener() {
                        @Override
                        public void onYesClick() {
                            dialog.dismiss();
                            getView().callBack();
                        }
                    });

                    dialog.setNoOnclickListener("直接支付", new SelfDialog.onNoOnclickListener() {
                        @Override
                        public void onNoClick() {
                            toPay(edit, payType, sellerId);
                            dialog.dismiss();
                        }
                    });
                } else {
                    toPay(edit, payType, sellerId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void toPay(String money, int type, String sellerId) {
        if (type == 0) {
            final IWXAPI api = WXAPIFactory.createWXAPI(mContext, CommonResource.WXAPPID, false);

            Map map = MapUtil.getInstance().addParms("totalAmout", money).addParms("orderSn", orderSn).addParms("productName", CommonResource.PROJECTNAME).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postData(CommonResource.WXPAY, map);
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

                        api.sendReq(request);
                        SPUtil.addParm("wxpay", "1");
                        getView().callBack();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "------------" + errorMsg);
                    getView().callBack();
                }
            }));
        } else if (type == 1) {
            Map map = MapUtil.getInstance().addParms("totalAmount", money).addParms("masterNo", orderSn).addParms("productName", CommonResource.PROJECTNAME).addParms("userCode", SPUtil.getUserCode()).build();
            ProcessDialogUtil.showProcessDialog(mContext);
//            WaitDialog.show((AppCompatActivity)mContext,null);

            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHead(CommonResource.TOPAY, map, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("支付宝：" + result);
                    AliPayBean aliPayBean = JSON.parseObject(result, AliPayBean.class);
                    info = aliPayBean.getBody();
                    Thread thread = new Thread(payRunnable);
                    thread.start();
                    getView().callBack();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    getView().callBack();
                }
            }));
        } else if (type == 2) {
            Map map = MapUtil.getInstance().addParms("masterNo", orderSn).addParms("totalAmount", money)
                    .addParms("productName", CommonResource.PROJECTNAME).addParms("userCode", SPUtil.getUserCode()).addParms("sellerId", sellerId).build();
            if (chooseCoupon != null) {
                map.put("couponId", chooseCoupon.getId());
            }
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).getData(CommonResource.LOCAL_BALANCE_PAY, map);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("余额支付：" + result);
                    Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    ((Activity) mContext).finish();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "--------------" + errorMsg);
                    if ("1".equals(errorCode)) {
                        Toast.makeText(mContext, "" + errorMsg, Toast.LENGTH_SHORT).show();
                    }
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

    public void createOrder(final LocalShopBean bean) {
        this.bean = bean;
        Map map = MapUtil.getInstance().addParms("sellerId", bean.getId()).addParms("sellerName", bean.getSeller_shop_name()).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHead(CommonResource.LOCAL_CREATEORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("创建订单：" + result);
                Map object = JSON.parseObject(result, Map.class);
                orderSn = (String) object.get("orderSn");
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------" + errorMsg);
                if (flag <= 4) {
                    createOrder(bean);
                    flag++;
                }
            }
        }));
    }

    public void getCoupon(LocalShopBean bean) {
        Map map = MapUtil.getInstance().addParms("status", "0").addParms("userCode", SPUtil.getUserCode()).addParms("sellerId", bean.getId()).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.QUERY_COUPON, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("可用优惠券：" + result);
                beanList = JSON.parseArray(result, UserCouponBean.class);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void choose(final String string) {
        if (beanList.size() > 0) {
            PopUtil.lingquanPop(mContext, beanList, new OnAdapterListener() {
                @Override
                public void setOnAdapterListener(final PopupWindow popupWindow, PopLingQuanAdapter adapter) {
                    adapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int index) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    chooseCoupon = beanList.get(index);
                                    popupWindow.dismiss();
                                    getView().chooseFinish(chooseCoupon);
                                    update(string);
                                }
                            });
                        }
                    });
                }
            });
        } else {
            Toast.makeText(mContext, "暂无可用优惠券", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(String edit) {
        if ("".equals(edit) || "0.".equals(edit)) {
            getView().updateMoney("0");
            isCanUse = true;
        } else {
            if (chooseCoupon != null) {
                if (chooseCoupon.getMinPoint() == 0) {
                    isCanUse = true;
                    getView().updateMoney(Double.valueOf(edit) - chooseCoupon.getAmount() + "");
                } else {
                    if (Double.valueOf(edit) >= chooseCoupon.getMinPoint()) {
                        isCanUse = true;
                        getView().updateMoney(Double.valueOf(edit) - chooseCoupon.getAmount() + "");
                    } else {
                        isCanUse = false;
                        getView().updateMoney(edit);
                    }
                }
            } else {
                getView().updateMoney(edit);
            }
        }
    }

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

    public void jumpToAssess() {
        Intent intent = new Intent(mContext, LocalAssessActivity.class);
        intent.putExtra("bean", bean);
        mContext.startActivity(intent);
    }
}

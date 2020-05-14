package com.example.order_confirm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.OrderConfirmBean;
import com.example.bean.PostageBean;
import com.example.bean.ShippingAddressBean;
import com.example.bean.SubmitOrderBean;
import com.example.bean.UserCouponBean;
import com.example.common.CommonResource;
import com.example.goods_detail.adapter.PopLingQuanAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.shop_home.ShopHomeActivity;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnAdapterListener;
import com.example.utils.PopUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.kongzue.dialog.v3.WaitDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class OrderConfirmPresneter extends BasePresenter<OrderConfirmView> {

    public ShippingAddressBean addressBean;
    public boolean isCan = false;
    private List<UserCouponBean> couponBeanList;
    private UserCouponBean chooseCoupon;

    public OrderConfirmPresneter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void jumpToShop(String sellerId) {
        ARouter.getInstance().build("/module_user_store/ShopHomeActivity")
                .withString("sellerId", sellerId).navigation();
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

    public void getPostage(OrderConfirmBean order) {
        Map map = MapUtil.getInstance().addParms("feightTemplateId", order.getFeightTemplateId())
                .addParms("provinceName", addressBean.getAddressProvince())
                .addParms("quantity", order.getQuantity())
                .addParms("skuId", order.getProductSkuId())
                .addParms("productId", order.getProductId())
                .build();
        List<Map> list = new ArrayList<>();
        list.add(map);
        String jsonString = JSON.toJSONString(list);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postHeadWithBody(CommonResource.GET_YUNGEI, requestBody, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("运费：" + result);
                isCan = true;
                List<PostageBean> postageBean = JSON.parseArray(result, PostageBean.class);
                getView().loadPostage(postageBean.get(0));
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                isCan = false;
                Toast.makeText(mContext, "没有获取到运费，请返回重试", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void jumpToShippingAddress() {
        ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").withString("from", "order").navigation((Activity) mContext, 123);
    }

    public void submit(final OrderConfirmBean bean) {
        if (bean.getReceiverProvince() == null || "".equals(bean.getReceiverProvince())) {
            Toast.makeText(mContext, "请选择收货地址", Toast.LENGTH_SHORT).show();
            getView().payFail();
        } else if (!isCan) {
            Toast.makeText(mContext, "未获取到运费信息，请重试", Toast.LENGTH_SHORT).show();
            getView().payFail();
        } else {
            ProcessDialogUtil.showProcessDialog(mContext);
//            WaitDialog.show((AppCompatActivity)mContext,null);
            String jsonString = JSON.toJSONString(bean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithBody(CommonResource.COMMIT_ORDER, requestBody, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("提交订单：" + result);
                    SubmitOrderBean submitOrderBean = JSON.parseObject(result, SubmitOrderBean.class);
                    submitOrderBean.setProductName("goods");
                    submitOrderBean.setProductCategoryId(bean.getProductCategoryId());

                    ARouter.getInstance().build("/module_user_store/PaymentActivity")
                            .withSerializable("submitOrderBean", submitOrderBean)
                            .navigation();
                    ((Activity) mContext).finish();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "--------" + errorMsg);
                    getView().payFail();
                }
            }));

        }
    }

    public void chooseCoupon(final OrderConfirmBean confirmBean, final double totalMoney) {
        if (isCan) {
            Map map = MapUtil.getInstance().addParms("status", "0").addParms("userCode", SPUtil.getUserCode()).addParms("sellerId", confirmBean.getSellerId()).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.QUERY_COUPON, map);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("可用优惠券：" + result);
                    getView().loadFinish();
                    try {
                        couponBeanList = JSON.parseArray(result, UserCouponBean.class);
                        if (couponBeanList != null && couponBeanList.size() > 0) {
                            PopUtil.lingquanPop(mContext, couponBeanList, new OnAdapterListener() {
                                @Override
                                public void setOnAdapterListener(final PopupWindow popupWindow, PopLingQuanAdapter adapter) {
                                    adapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(RecyclerView parent, View view, int index) {
                                            if (couponBeanList.get(index).getMinPoint() <= totalMoney) {
                                                chooseCoupon = couponBeanList.get(index);
                                                getView().couponChoosed(chooseCoupon);
                                                popupWindow.dismiss();
                                            } else {
                                                Toast.makeText(mContext, "条件不满足", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    getView().loadFinish();
                    LogUtil.e(errorCode + "--------------" + errorMsg);
                }
            }));
        } else {
            Toast.makeText(mContext, "正在获取数据，请稍后", Toast.LENGTH_SHORT).show();
        }
    }
}

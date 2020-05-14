package com.example.confirm_order;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.CartBean;
import com.example.bean.ConfirmOrderBean;
import com.example.bean.ConfirmOrderInsideBean;
import com.example.bean.PostageBean;
import com.example.bean.ShippingAddressBean;
import com.example.bean.SubmitOrderBean;
import com.example.bean.UserCouponBean;
import com.example.common.CommonResource;
import com.example.confirm_order.adapter.ConfirmOrderAdapter;
import com.example.goods_detail.adapter.PopLingQuanAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnAdapterListener;
import com.example.utils.PopUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ConfirmOrderPresenter extends BasePresenter<ConfirmOrderView> {
    private ConfirmOrderAdapter orderAdapter;
    public ShippingAddressBean addressBean;
    private List<CartBean.RecordsBean> dataList = new ArrayList<>();
    public boolean isCan = false;

    public ConfirmOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(List<CartBean.RecordsBean> beanList) {
        dataList.addAll(beanList);
        orderAdapter = new ConfirmOrderAdapter(mContext, dataList, R.layout.rv_confirm_order);

        if (getView() != null) {
            getView().loadRv(orderAdapter);
        }

        orderAdapter.setViewThreeOnClickListener2(new MyRecyclerAdapter.ViewThreeOnClickListener2() {
            @Override
            public void viewThreeOnClick2(final View minus, final View add, final View chooseCoupon, final int outside, final int inside) {
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isCan) {
                            minus.setEnabled(false);
                            if (dataList.get(outside).getTotalPrice() - dataList.get(outside).getItems().get(inside).getPrice() >= dataList.get(outside).getMinAmount()) {
                                if (dataList.get(outside).getItems().get(inside).getQuantity() <= 1) {
                                    dataList.get(outside).getItems().get(inside).setQuantity(1);
                                } else {
                                    dataList.get(outside).getItems().get(inside).setQuantity(dataList.get(outside).getItems().get(inside).getQuantity() - 1);
                                    getPostage(addressBean.getAddressProvince());
                                }
                                ProcessDialogUtil.showProcessDialog(mContext);
                                reviseStutas(dataList.get(outside).getItems(), minus);

                            } else {
                                Toast.makeText(mContext, "不符合条件", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(mContext, "正在获取数据，请稍后", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isCan) {
                            add.setEnabled(false);
                            dataList.get(outside).getItems().get(inside).setQuantity(dataList.get(outside).getItems().get(inside).getQuantity() + 1);
                            ProcessDialogUtil.showProcessDialog(mContext);
                            getPostage(addressBean.getAddressProvince());
                            reviseStutas(dataList.get(outside).getItems(), add);

                        } else {
                            Toast.makeText(mContext, "正在获取数据，请稍后", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                chooseCoupon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chooseCoupon.setEnabled(false);
                        chooseCoupon(dataList.get(outside).getSellerId(), outside, chooseCoupon);
                    }
                });
            }

            @Override
            public void ViewThreeOnClick(View view1, View view2, View view3, int position) {

            }
        });
    }

    private void reviseStutas(List<CartBean.RecordsBean.ItemsBean> dataList, final View view) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithList(CommonResource.REVISE_CART_ITEM, dataList, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("修改状态：" + result);
                view.setEnabled(true);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                view.setEnabled(true);
            }
        }));
    }


    public void chooseCoupon(int sellerId, final int outside, final View chooseCoupon) {
        Map map = MapUtil.getInstance().addParms("status", "0").addParms("userCode", SPUtil.getUserCode()).addParms("sellerId", sellerId).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.QUERY_COUPON, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("可用优惠券：" + result);
                chooseCoupon.setEnabled(true);
                try {

                    final List<UserCouponBean> couponBeanList = JSON.parseArray(result, UserCouponBean.class);
                    if (couponBeanList != null && couponBeanList.size() > 0) {
                        PopUtil.lingquanPop(mContext, couponBeanList, new OnAdapterListener() {
                            @Override
                            public void setOnAdapterListener(final PopupWindow popupWindow, PopLingQuanAdapter adapter) {
                                adapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(RecyclerView parent, View view, int index) {
                                        if (couponBeanList.get(index).getMinPoint() <= dataList.get(outside).getTotalPrice()) {
                                            dataList.get(outside).setDisAmount(couponBeanList.get(index).getAmount());
                                            dataList.get(outside).setMinAmount(couponBeanList.get(index).getMinPoint());
                                            dataList.get(outside).setCouponId(couponBeanList.get(index).getId());
                                            dataList.get(outside).setTotalPrice(dataList.get(outside).getTotalPrice() - couponBeanList.get(index).getAmount());
                                            orderAdapter.notifyDataSetChanged();
                                            double disAmount = 0.0;
                                            for (int i = 0; i < dataList.size(); i++) {
                                                disAmount += dataList.get(i).getDisAmount();
                                            }
                                            getView().couponAfter(disAmount);
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
                    chooseCoupon.setEnabled(true);
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
                Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    public void jumpToPayment() {
        if (isCan) {
            List<ConfirmOrderInsideBean> list = new ArrayList<>();
            for (int i = 0; i < dataList.size(); i++) {
                ConfirmOrderInsideBean insideBean = new ConfirmOrderInsideBean();
                insideBean.setSellerId((long) dataList.get(i).getSellerId());
                insideBean.setFreightAmount(dataList.get(i).getTotalFeight());
                insideBean.setCouponAmount(dataList.get(i).getDisAmount());
                insideBean.setCouponId(dataList.get(i).getCouponId());
                list.add(insideBean);
            }
            ConfirmOrderBean orderBean = new ConfirmOrderBean();

            orderBean.setReceiverName(addressBean.getAddressName());
            orderBean.setReceiverPhone(addressBean.getAddressPhone());
            orderBean.setReceiverProvince(addressBean.getAddressProvince());
            orderBean.setReceiverCity(addressBean.getAddressCity());
            orderBean.setReceiverRegion(addressBean.getAddressArea());
            orderBean.setOrderAddress(addressBean.getAddressDetail());
            orderBean.setUserId(SPUtil.getUserCode());
            orderBean.setOrderRequestItems(list);

            ProcessDialogUtil.showProcessDialog(mContext);

            String jsonString = JSON.toJSONString(orderBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithBody(CommonResource.CART_SUBMIT_ORDER, requestBody, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("购物车下单：" + result);
                    SubmitOrderBean submitOrderBean = JSON.parseObject(result, SubmitOrderBean.class);
                    submitOrderBean.setProductCategoryId(dataList.get(0).getItems().get(0).getProductCategoryId() + "");
                    submitOrderBean.setProductName("cart");
                    ARouter.getInstance().build("/module_user_store/PaymentActivity")
                            .withSerializable("submitOrderBean", submitOrderBean)
                            .navigation();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "--------------" + errorMsg);
                }
            }));
        } else {
            Toast.makeText(mContext, "未获取到运费信息，请重试", Toast.LENGTH_SHORT).show();
        }
    }

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

    public void getPostage(String province) {
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = 0; j < dataList.get(i).getItems().size(); j++) {
                list.add(MapUtil.getInstance()
                        .addParms("provinceName", province)
                        .addParms("quantity", dataList.get(i).getItems().get(j).getQuantity())
                        .addParms("skuId", dataList.get(i).getItems().get(j).getProductSkuId())
                        .addParms("productId", dataList.get(i).getItems().get(j).getProductId())
                        .build());
            }
        }

        String jsonString = JSON.toJSONString(list);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postHeadWithBody(CommonResource.GET_YUNGEI, requestBody, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("运费：" + result);
                isCan = true;
                List<PostageBean> postageBean = JSON.parseArray(result, PostageBean.class);


                for (int j = 0; j < dataList.size(); j++) {
                    double feight = 0;
                    double totalPrice = 0;
                    for (int i = 0; i < postageBean.size(); i++) {
                        if (postageBean.get(i).getSellerId() == dataList.get(j).getSellerId()) {
                            feight += postageBean.get(i).getFeight();
                            totalPrice += postageBean.get(i).getTotal();
                        }
                    }
                    dataList.get(j).setTotalFeight(feight);
                    dataList.get(j).setTotalPrice(ArithUtil.exact(totalPrice, 2));
                }
                orderAdapter.notifyDataSetChanged();
                jisuan(postageBean);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                isCan = false;
                LogUtil.e("yunfei:" + errorCode + "---------" + errorMsg);
            }
        }));
    }

    private void jisuan(List<PostageBean> postageBean) {
        double totalFeight = 0;
        double totalPrice = 0.00;
        for (int i = 0; i < postageBean.size(); i++) {
            totalFeight += postageBean.get(i).getFeight();
            totalPrice += postageBean.get(i).getTotal();
        }
        if (getView() != null) {
            getView().loadPostage(totalFeight, totalPrice, postageBean.size());
        }
    }

    public void jumpToShippingAddress() {
        ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").withString("from", "order").navigation((Activity) mContext, 456);
    }
}

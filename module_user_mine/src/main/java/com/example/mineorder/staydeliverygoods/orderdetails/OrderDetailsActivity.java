package com.example.mineorder.staydeliverygoods.orderdetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.OrderDetailBean;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 多用户订单详情
 */
@Route(path = "/module_user_mine/OrderDetailsActivity")
public class OrderDetailsActivity extends BaseActivity<OrderDetailsView, OrderDetailsPresenter> implements OrderDetailsView {

    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.order_details_status)
    TextView orderDetailsStatus;
    @BindView(R2.id.order_details_subhead)
    TextView orderDetailsSubhead;
    @BindView(R2.id.order_details_name)
    TextView orderDetailsName;
    @BindView(R2.id.order_details_phone)
    TextView orderDetailsPhone;
    @BindView(R2.id.order_details_address)
    TextView orderDetailsAddress;
    @BindView(R2.id.order_details_goods_rec)
    RecyclerView orderDetailsGoodsRec;
    @BindView(R2.id.order_details_goods_price)
    TextView orderDetailsGoodsPrice;
    @BindView(R2.id.order_details_freight)
    TextView orderDetailsFreight;
    @BindView(R2.id.order_details_coupon)
    TextView orderDetailsCoupon;
    @BindView(R2.id.order_details_actual_payment)
    TextView orderDetailsActualPayment;
    @BindView(R2.id.order_details_contact_seller)
    LinearLayout orderDetailsContactSeller;
    @BindView(R2.id.order_details_dial)
    LinearLayout orderDetailsDial;
    @BindView(R2.id.order_details_consult_customer_service)
    LinearLayout orderDetailsConsultCustomerService;
    @BindView(R2.id.order_details_recommend_rec)
    RecyclerView orderDetailsRecommendRec;
    @BindView(R2.id.order_details_left)
    TextView orderDetailsLeft;
    @BindView(R2.id.order_details_refund)
    TextView orderDetailsRefund;
    @BindView(R2.id.order_details_right)
    TextView orderDetailsRight;

    @Autowired(name = "orderSn")
    String orderSn;

    private int status;
    private OrderDetailBean orderDetailBean;
    private CountDownTimer countDownTimer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initData() {
        includeTitle.setText("订单详情");
        ARouter.getInstance().inject(this);
        presenter.initView(orderSn);

        presenter.orderDetailsRecommendRec(orderDetailsRecommendRec);
    }

    @Override
    public void initClick() {

        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        orderDetailsContactSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(CommonResource.SERVICE_PHONE);
            }
        });

        orderDetailsConsultCustomerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("CommonResource.SERVICE_PHONE");
            }
        });

        orderDetailsLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1 == status) {
                    if ("申请退款".equals(orderDetailsLeft.getText().toString())) {
                        ARouter.getInstance()
                                .build("/module_user_mine/RefundActivity")
                                .withSerializable("orderDetailBean", orderDetailBean)
                                .withString("type", "2")
                                .navigation();
                    }
                } else {
                    //查看物流
                    ARouter.getInstance()
                            .build("/module_user_mine/LogisticsInformationActivity")
                            .withString("orderSn", orderDetailBean.getOrderSn())
                            .withString("goodsImage", orderDetailBean.getItems().get(0).getProductPic())
                            .navigation();
                }
            }
        });
        //提醒发货
        orderDetailsRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1 == status) {
                    Toast.makeText(OrderDetailsActivity.this, "已提醒商家发货!", Toast.LENGTH_SHORT).show();
                } else {
                    ProcessDialogUtil.showProcessDialog(OrderDetailsActivity.this);
                    Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithout(CommonResource.ORDERCONFIRM + "/" + orderDetailBean.getItems().get(0).getOrderId(), SPUtil.getToken());
                    RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
                        @Override
                        public void onSuccess(String result, String msg) {
                            LogUtil.e("确认收货---->" + result);
                            finish();
                        }

                        @Override
                        public void onError(String errorCode, String errorMsg) {
                            LogUtil.e("确认收货error---->" + errorMsg);
                        }
                    }));
                }

            }
        });
        //申请退款
        orderDetailsRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("申请退款".equals(orderDetailsRefund.getText().toString())) {
                    ARouter.getInstance()
                            .build("/module_user_mine/RefundActivity")
                            .withSerializable("orderDetailBean", orderDetailBean)
                            .withString("type", "2")
                            .navigation();
                }
            }
        });

    }

    @Override
    public OrderDetailsView createView() {
        return this;
    }

    @Override
    public OrderDetailsPresenter createPresenter() {
        return new OrderDetailsPresenter(this);
    }

    //调起电话
    private void call(String call) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + call));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //开启系统拨号器
        startActivity(intent);

    }

    @Override
    public void loadData(final OrderDetailBean orderDetailBean) {
        this.orderDetailBean = orderDetailBean;
        this.status = orderDetailBean.getStatus();

        List<OrderDetailBean.ItemsBean> items = orderDetailBean.getItems();
        presenter.items(items, orderDetailsGoodsRec);

        orderDetailsName.setText(orderDetailBean.getReceiverName());
        orderDetailsPhone.setText(orderDetailBean.getReceiverPhone());
        orderDetailsAddress.setText(orderDetailBean.getReceiverRegion() + orderDetailBean.getReceiverCity() + orderDetailBean.getReceiverProvince() + orderDetailBean.getOrderAddress());
        orderDetailsGoodsPrice.setText("￥" + orderDetailBean.getTotalAmount());
        orderDetailsFreight.setText("+￥" + orderDetailBean.getFreightAmount());
        orderDetailsCoupon.setText("-￥" + orderDetailBean.getCouponAmount());
        orderDetailsActualPayment.setText("￥" + orderDetailBean.getPayAmount());


        if (orderDetailBean.getStatus() == 1) {
            //待发货
            orderDetailsSubhead.setVisibility(View.GONE);
            orderDetailsRefund.setVisibility(View.GONE);
            orderDetailsLeft.setVisibility(View.GONE);
            orderDetailsRight.setVisibility(View.GONE);
            orderDetailsLeft.setText("申请退款");
            orderDetailsRight.setText("提醒发货");
            if (0 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("等待卖家处理");
            } else if (1 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("退货中");
            } else if (2 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("退货完成");
            } else if (3 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("卖家已拒绝");
                orderDetailsRight.setVisibility(View.VISIBLE);
            } else if (-1 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("购买成功（待发货）");
                orderDetailsLeft.setVisibility(View.VISIBLE);
                orderDetailsRight.setVisibility(View.VISIBLE);
            } else if (4 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("已取消退款");
                orderDetailsRight.setVisibility(View.VISIBLE);
            }
        } else if (orderDetailBean.getStatus() == 2) {

            //待收货
            orderDetailsSubhead.setVisibility(View.VISIBLE);
            orderDetailsRefund.setVisibility(View.GONE);
            orderDetailsLeft.setVisibility(View.VISIBLE);
            orderDetailsRight.setVisibility(View.VISIBLE);
            orderDetailsLeft.setText("查看物流");
            orderDetailsRight.setText("确认收货");
            if (0 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("等待卖家处理");
            } else if (1 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("退货中");
            } else if (2 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("退货完成");
                orderDetailsLeft.setVisibility(View.GONE);
                orderDetailsRight.setVisibility(View.GONE);
            } else if (3 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("卖家已拒绝");
            } else if (-1 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("卖家已发货");
                orderDetailsRefund.setText("申请退款");
                orderDetailsRefund.setVisibility(View.VISIBLE);
            } else if (4 == orderDetailBean.getBackStatus()) {
                orderDetailsStatus.setText("已取消退款");
            }
            time(orderDetailBean.getReceiveTime());
        }

    }

    private void time(String orderOutTime) {
//        long firstTime = System.currentTimeMillis() + 30 * 60 * 1000;
        long timeStamp = getTimeStamp(orderOutTime, "yyyy-MM-dd HH:mm:ss");
        LogUtil.e("firstTime----------->" + timeStamp);
        long time = timeStamp - System.currentTimeMillis();
        LogUtil.e("time------------->" + time / 1000);
        //第一个参数表示总时间，第二个参数表示间隔时间。
        countDownTimer = new CountDownTimer(time, 1000) {//第一个参数表示总时间，第二个参数表示间隔时间。

            @Override
            public void onTick(long millisUntilFinished) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd天HH小时mm分");
                String dateString = formatter.format(millisUntilFinished);

                orderDetailsSubhead.setText("还剩" + dateString + "自动收货");
            }

            @Override
            public void onFinish() {
                LogUtil.e("結束");
            }
        }.start();
    }

    /**
     * 时间转换为时间戳
     *
     * @param timeStr 时间 例如: 2016-03-09
     * @param format  时间对应格式  例如: yyyy-MM-dd
     * @return
     */
    public static long getTimeStamp(String timeStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(timeStr);
            long timeStamp = date.getTime();
            return timeStamp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.onFinish();
            countDownTimer.cancel();
        }
    }
}

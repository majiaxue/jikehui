package com.example.obligation;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.OrderDetailBean;
import com.example.bean.SubmitOrderBean;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 等待付款
 */
@Route(path = "/module_user_mine/ObligationActivity")
public class ObligationActivity extends BaseActivity<ObligationView, ObligationPresenter> implements ObligationView {

    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.obligation_name)
    TextView obligationName;
    @BindView(R2.id.obligation_phone)
    TextView obligationPhone;
    @BindView(R2.id.obligation_address)
    TextView obligationAddress;
    @BindView(R2.id.obligation_relative)
    RelativeLayout obligationRelative;
    @BindView(R2.id.obligation_goods_money)
    TextView obligationGoodsMoney;
    @BindView(R2.id.obligation_freight)
    TextView obligationFreight;
    @BindView(R2.id.obligation_coupon)
    TextView obligationCoupon;
    @BindView(R2.id.obligation_money)
    TextView obligationMoney;
    @BindView(R2.id.obligation_time_remaining)
    TextView obligationTimeRemaining;
    @BindView(R2.id.obligation_cancellation_order)
    TextView obligationCancellationOrder;
    @BindView(R2.id.obligation_payment)
    TextView obligationPayment;
    @BindView(R2.id.obligation_rec)
    RecyclerView obligationRec;
    @BindView(R2.id.obligation_goods_rec)
    RecyclerView obligationGoodsRec;

    @Autowired(name = "orderSn")
    String orderSn;

    private OrderDetailBean orderDetailBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_obligation;
    }

    @Override
    public void initData() {
        includeTitle.setText("等待付款");
        ARouter.getInstance().inject(this);
        LogUtil.e("订单编号------->" + orderSn);
        presenter.initView(orderSn);
        presenter.obligationRec(obligationRec);

//        time();
    }

    private void time(String orderOutTime) {
//        long firstTime = System.currentTimeMillis() + 30 * 60 * 1000;
        long timeStamp = getTimeStamp(orderOutTime, "yyyy-MM-dd HH:mm:ss");
        LogUtil.e("firstTime----------->" + timeStamp);
        long time = timeStamp - System.currentTimeMillis();
        LogUtil.e("time------------->" + time / 1000);
        CountDownTimer countDownTimer = new CountDownTimer(time, 1000) {//第一个参数表示总时间，第二个参数表示间隔时间。

            @Override
            public void onTick(long millisUntilFinished) {
                SimpleDateFormat formatter = new SimpleDateFormat("mm分ss秒");
                String dateString = formatter.format(millisUntilFinished);

                obligationTimeRemaining.setText("剩余" + dateString);
            }

            @Override
            public void onFinish() {
                LogUtil.e("結束");
            }
        }.start();
    }

    @Override
    public void initClick() {

        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        obligationCancellationOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupCancellationOrder();
            }
        });

        //付款
        obligationPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitOrderBean submitOrderBean = new SubmitOrderBean();
                submitOrderBean.setTotalAmount(orderDetailBean.getPayAmount());
                submitOrderBean.setMasterNo(orderDetailBean.getMasterSn());
                ARouter.getInstance().build("/module_user_store/PaymentActivity")
                        .withSerializable("submitOrderBean", submitOrderBean)
                        .navigation();
            }
        });
    }

    @Override
    public ObligationView createView() {
        return this;
    }

    @Override
    public ObligationPresenter createPresenter() {
        return new ObligationPresenter(this);
    }

    @Override
    public void loadData(final OrderDetailBean orderDetailBean) {
        this.orderDetailBean = orderDetailBean;
        time(orderDetailBean.getOrderOutTime());
        obligationName.setText(orderDetailBean.getReceiverName());
        obligationPhone.setText(orderDetailBean.getReceiverPhone());
        obligationAddress.setText(orderDetailBean.getReceiverRegion() + orderDetailBean.getReceiverCity() + orderDetailBean.getReceiverProvince() + orderDetailBean.getOrderAddress());
        obligationName.setText(orderDetailBean.getReceiverName());
        obligationGoodsMoney.setText("￥" + orderDetailBean.getTotalAmount());
        obligationFreight.setText("￥" + orderDetailBean.getFreightAmount());
        obligationCoupon.setText("￥" + orderDetailBean.getCouponAmount());
        obligationMoney.setText("￥" + orderDetailBean.getPayAmount());

        List<OrderDetailBean.ItemsBean> items = orderDetailBean.getItems();

        presenter.items(items, obligationGoodsRec);

    }

    @Override
    public void isDelete(boolean isDelete) {
        if (isDelete) {
            finish();
        }
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


}

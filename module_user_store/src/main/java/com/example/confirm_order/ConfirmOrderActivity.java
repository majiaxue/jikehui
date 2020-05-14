package com.example.confirm_order;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.bean.CartBean;
import com.example.bean.ShippingAddressBean;
import com.example.confirm_order.adapter.ConfirmOrderAdapter;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.ArithUtil;
import com.example.utils.SpaceItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * 确认订单
 */

public class ConfirmOrderActivity extends BaseActivity<ConfirmOrderView, ConfirmOrderPresenter> implements ConfirmOrderView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.confirm_order_name)
    TextView confirmOrderName;
    @BindView(R2.id.confirm_order_phone)
    TextView confirmOrderPhone;
    @BindView(R2.id.confirm_order_detail)
    TextView mDetail;
    @BindView(R2.id.confirm_order_choose_address)
    TextView confirmOrderChooseAddress;
    @BindView(R2.id.confirm_order_rela)
    RelativeLayout confirmOrderRela;
    @BindView(R2.id.confirm_order_rv)
    RecyclerView confirmOrderRv;
    @BindView(R2.id.confirm_order_total_price)
    TextView confirmOrderTotalPrice;
    @BindView(R2.id.confirm_order_total_yunfei)
    TextView confirmOrderTotalYunfei;
    @BindView(R2.id.confirm_order_total_coupon)
    TextView confirmOrderTotalCoupon;
    @BindView(R2.id.confirm_order_final_price)
    TextView confirmOrderFinalPrice;
    @BindView(R2.id.confirm_order_submit)
    TextView confirmOrderSubmit;
    @BindView(R2.id.confirm_order_count)
    TextView mCount;

    private double couponMoney = 0.0;
    private double totalMoney;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initData() {
        includeTitle.setText("确认订单");
        Intent intent = getIntent();
        String bean = intent.getStringExtra("bean");
        List<CartBean.RecordsBean> beanList = JSON.parseArray(bean, CartBean.RecordsBean.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        confirmOrderRv.setLayoutManager(layoutManager);
        if (confirmOrderRv.getItemDecorationCount() < 1) {
            confirmOrderRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_10)));
        }
        presenter.loadData(beanList);
        presenter.getAddress();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmOrderSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToPayment();
            }
        });

        confirmOrderRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShippingAddress();
            }
        });

        confirmOrderChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShippingAddress();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            ShippingAddressBean addressBean = (ShippingAddressBean) data.getSerializableExtra("address");
            presenter.addressBean = addressBean;
            presenter.isCan = false;
            loadAddress(addressBean);
        }
    }

    @Override
    public void loadAddress(ShippingAddressBean addressBean) {
        confirmOrderName.setText(addressBean.getAddressName());
        confirmOrderPhone.setText(addressBean.getAddressPhone());
        mDetail.setText(addressBean.getAddressProvince() + addressBean.getAddressCity() + addressBean.getAddressArea() + addressBean.getAddressDetail());
        confirmOrderChooseAddress.setVisibility(View.GONE);
        presenter.getPostage(addressBean.getAddressProvince());
    }

    @Override
    public void loadPostage(double feight, double price, int number) {
        totalMoney = price;
        confirmOrderTotalYunfei.setText("+￥" + feight);
        confirmOrderTotalPrice.setText("￥" + ArithUtil.sub(price, feight));
        confirmOrderFinalPrice.setText(ArithUtil.sub(price, couponMoney) + "");
        mCount.setText("共" + number + "件");
    }

    @Override
    public void couponAfter(double amount) {
        couponMoney = amount;
        confirmOrderTotalCoupon.setText("-￥" + couponMoney);
        if (totalMoney != 0) {
            confirmOrderFinalPrice.setText(totalMoney - couponMoney + "");
        }
    }

    @Override
    public void noAddress() {
        confirmOrderChooseAddress.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadRv(ConfirmOrderAdapter adapter) {
        confirmOrderRv.setAdapter(adapter);
    }

    @Override
    public ConfirmOrderView createView() {
        return this;
    }

    @Override
    public ConfirmOrderPresenter createPresenter() {
        return new ConfirmOrderPresenter(this);
    }
}

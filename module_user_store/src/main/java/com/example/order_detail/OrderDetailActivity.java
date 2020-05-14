package com.example.order_detail;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bean.OrderDetailBean;
import com.example.bean.SubmitOrderBean;
import com.example.mvp.BaseActivity;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.RvItemDecoration;

import butterknife.BindView;

public class OrderDetailActivity extends BaseActivity<OrderDetailView, OrderDetailPresenter> implements OrderDetailView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.order_detail_name)
    TextView orderDetailName;
    @BindView(R2.id.order_detail_phone)
    TextView orderDetailPhone;
    @BindView(R2.id.order_detail_address)
    TextView orderDetailAddress;
    @BindView(R2.id.order_detail_img)
    ImageView orderDetailImg;
    @BindView(R2.id.order_detail_goods)
    TextView orderDetailGoods;
    @BindView(R2.id.order_detail_color)
    TextView orderDetailColor;
    @BindView(R2.id.order_detail_price)
    TextView orderDetailPrice;
    @BindView(R2.id.order_detail_count)
    TextView orderDetailCount;
    @BindView(R2.id.order_detail_total_price)
    TextView orderDetailTotalPrice;
    @BindView(R2.id.order_detail_yunfei)
    TextView orderDetailYunfei;
    @BindView(R2.id.order_detail_coupon)
    TextView orderDetailCoupon;
    @BindView(R2.id.order_detail_lianximaijia)
    TextView orderDetailLianximaijia;
    @BindView(R2.id.order_detail_bodadianhua)
    TextView orderDetailBodadianhua;
    @BindView(R2.id.order_detail_zixunkefu)
    TextView orderDetailZixunkefu;
    @BindView(R2.id.order_detail_commend)
    RecyclerView orderDetailCommend;
    @BindView(R2.id.order_detail_tuikuan)
    TextView orderDetailTuikuan;
    @BindView(R2.id.order_detail_fahuo)
    TextView orderDetailFahuo;
    @BindView(R2.id.order_detail_shifu)
    TextView mShifu;
    private SubmitOrderBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initData() {
        includeTitle.setText("订单详情");
        Intent intent = getIntent();
        bean = (SubmitOrderBean) intent.getSerializableExtra("bean");

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        orderDetailCommend.setLayoutManager(staggeredGridLayoutManager);
        orderDetailCommend.addItemDecoration(new RvItemDecoration((int) getResources().getDimension(R.dimen.dp_12), (int) getResources().getDimension(R.dimen.dp_12)));
        presenter.loadOrder(bean.getMasterNo());
        presenter.loadCommend(bean.getProductCategoryId());
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        orderDetailFahuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderDetailActivity.this, "提醒成功", Toast.LENGTH_SHORT).show();
                orderDetailFahuo.setEnabled(false);
                orderDetailFahuo.setTextColor(getResources().getColor(R.color.color999));
            }
        });

        orderDetailTuikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToTuikuan(bean);
            }
        });
    }

    @Override
    public void loadData(OrderDetailBean orderDetailBean) {
        orderDetailName.setText(orderDetailBean.getReceiverName());
        orderDetailPhone.setText(orderDetailBean.getReceiverPhone());
        orderDetailAddress.setText(orderDetailBean.getReceiverProvince() + orderDetailBean.getReceiverCity() + orderDetailBean.getOrderAddress());
        Glide.with(this).load(orderDetailBean.getItems().get(0).getProductPic()).into(orderDetailImg);
        orderDetailGoods.setText(orderDetailBean.getItems().get(0).getProductName());
        orderDetailColor.setText(orderDetailBean.getItems().get(0).getProductAttr());
        orderDetailCount.setText("x" + orderDetailBean.getItems().get(0).getProductQuantity());
        orderDetailPrice.setText("￥" + orderDetailBean.getItems().get(0).getProductPrice());
        orderDetailTotalPrice.setText("￥" + orderDetailBean.getTotalAmount());
        orderDetailYunfei.setText("+￥" + orderDetailBean.getFreightAmount());
        orderDetailCoupon.setText("-￥" + orderDetailBean.getCouponAmount());
        mShifu.setText("￥" + orderDetailBean.getPayAmount());
    }

    @Override
    public void loadCommend(CommendAdapter adapter) {
        orderDetailCommend.setAdapter(adapter);
    }

    @Override
    public OrderDetailView createView() {
        return this;
    }

    @Override
    public OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter(this);
    }
}

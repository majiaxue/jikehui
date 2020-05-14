package com.example.order_confirm;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.bean.OrderConfirmBean;
import com.example.bean.PostageBean;
import com.example.bean.ShippingAddressBean;
import com.example.bean.UserCouponBean;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.ArithUtil;

import java.text.DecimalFormat;

import butterknife.BindView;

@Route(path = "/user/order_confirm")
public class OrderConfirmActivity extends BaseActivity<OrderConfirmView, OrderConfirmPresneter> implements OrderConfirmView {

    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.order_confirm_rela)
    RelativeLayout mRela;
    @BindView(R2.id.order_confirm_name)
    TextView orderConfirmName;
    @BindView(R2.id.order_confirm_phone)
    TextView orderConfirmPhone;
    @BindView(R2.id.order_confirm_address_detail)
    TextView mAddressDetail;
    @BindView(R2.id.order_confirm_choose_address)
    TextView orderConfirmChooseAddress;
    @BindView(R2.id.order_confirm_shop_name)
    TextView orderConfirmShopName;
    @BindView(R2.id.order_confirm_goshop)
    LinearLayout orderConfirmGoshop;
    @BindView(R2.id.order_confirm_img)
    ImageView orderConfirmImg;
    @BindView(R2.id.order_confirm_goods)
    TextView orderConfirmGoods;
    @BindView(R2.id.order_confirm_color)
    TextView orderConfirmColor;
    @BindView(R2.id.order_confirm_price)
    TextView orderConfirmPrice;
    @BindView(R2.id.order_confirm_minus)
    TextView orderConfirmMinus;
    @BindView(R2.id.order_confirm_count)
    TextView orderConfirmCount;
    @BindView(R2.id.order_confirm_add)
    TextView orderConfirmAdd;
    @BindView(R2.id.order_confirm_delivery_txt1)
    TextView orderConfirmDeliveryTxt1;
    @BindView(R2.id.order_confirm_delivery_txt2)
    TextView orderConfirmDeliveryTxt2;
    @BindView(R2.id.order_confirm_delivery)
    LinearLayout orderConfirmDelivery;
    @BindView(R2.id.order_confirm_coupon_txt)
    TextView orderConfirmCouponTxt;
    @BindView(R2.id.order_confirm_coupon)
    LinearLayout orderConfirmCoupon;
    @BindView(R2.id.order_confirm_edit)
    EditText orderConfirmEdit;
    @BindView(R2.id.order_confirm_goods_count)
    TextView orderConfirmGoodsCount;
    @BindView(R2.id.order_confirm_xiaoji)
    TextView orderConfirmXiaoji;
    @BindView(R2.id.order_confirm_total_price)
    TextView orderConfirmTotalPrice;
    @BindView(R2.id.order_confirm_total_yunfei)
    TextView orderConfirmTotalYunfei;
    @BindView(R2.id.order_confirm_total_coupon)
    TextView orderConfirmTotalCoupon;
    @BindView(R2.id.order_confirm_final_price)
    TextView orderConfirmFinalPrice;
    @BindView(R2.id.order_confirm_submit)
    TextView orderConfirmSubmit;

    private OrderConfirmBean confirmBean;
    private double totalMoney = 0.0;
    private double couponMoney = 0.0;
    private double minAmount = -125;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_confirm;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        confirmBean = (OrderConfirmBean) intent.getSerializableExtra("order");
        includeTitle.setText("确认订单");
        orderConfirmShopName.setText(confirmBean.getSellerName());
        Glide.with(this).load(confirmBean.getPic()).into(orderConfirmImg);
        orderConfirmGoods.setText(confirmBean.getProductName());
        orderConfirmColor.setText(confirmBean.getProductAttr());
        orderConfirmPrice.setText(confirmBean.getPrice() + "");
        orderConfirmCount.setText(confirmBean.getQuantity() + "");
        orderConfirmGoodsCount.setText("共" + confirmBean.getQuantity() + "件");
        orderConfirmTotalPrice.setText("￥" + ArithUtil.mul(confirmBean.getPrice(), confirmBean.getQuantity()));


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

        orderConfirmGoshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShop(confirmBean.getSellerId());
            }
        });

        orderConfirmChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShippingAddress();
            }
        });

        mRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShippingAddress();
            }
        });

        orderConfirmMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.isCan) {
                    if (confirmBean.getQuantity() > 1) {
                        if (minAmount != -125 && (totalMoney - confirmBean.getPrice()) >= minAmount) {
                            confirmBean.setQuantity(confirmBean.getQuantity() - 1);
                            presenter.getPostage(confirmBean);
                        } else if (minAmount == -125) {
                            confirmBean.setQuantity(confirmBean.getQuantity() - 1);
                            presenter.getPostage(confirmBean);
                        } else {
                            Toast.makeText(OrderConfirmActivity.this, "不符合优惠券要求", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(OrderConfirmActivity.this, "没有收货地址或未获取到运费", Toast.LENGTH_SHORT).show();
                }
            }
        });

        orderConfirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.isCan) {
                    if (confirmBean.getQuantity() < confirmBean.getStock()) {
                        confirmBean.setQuantity(confirmBean.getQuantity() + 1);
                        presenter.getPostage(confirmBean);
                    }
                } else {
                    Toast.makeText(OrderConfirmActivity.this, "没有收货地址或未获取到运费", Toast.LENGTH_SHORT).show();
                }
            }
        });

        orderConfirmSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderConfirmSubmit.setEnabled(false);
                confirmBean.setRemark(orderConfirmEdit.getText().toString());
                presenter.submit(confirmBean);
            }
        });

        orderConfirmCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderConfirmCoupon.setEnabled(false);
                presenter.chooseCoupon(confirmBean, totalMoney);
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
    public void couponChoosed(UserCouponBean coupon) {
        couponMoney = coupon.getAmount();
        minAmount = coupon.getMinPoint();
        orderConfirmCouponTxt.setText("优惠￥" + coupon.getAmount() + "元");
        orderConfirmTotalCoupon.setText("-￥" + coupon.getAmount());
        confirmBean.setCouponAmount(coupon.getAmount());
        confirmBean.setCouponId(coupon.getId());
        orderConfirmFinalPrice.setText("" + ArithUtil.sub(Double.valueOf(orderConfirmFinalPrice.getText().toString()), coupon.getAmount()));
    }

    @Override
    public void payFail() {
        orderConfirmSubmit.setEnabled(true);
    }

    @Override
    public void noAddress() {
        orderConfirmChooseAddress.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadAddress(ShippingAddressBean addressBean) {
        orderConfirmName.setText(addressBean.getAddressName());
        orderConfirmPhone.setText(addressBean.getAddressPhone());
        mAddressDetail.setText(addressBean.getAddressProvince() + addressBean.getAddressCity() + addressBean.getAddressArea() + addressBean.getAddressDetail());

        confirmBean.setReceiverName(addressBean.getAddressName());
        confirmBean.setReceiverPhone(addressBean.getAddressPhone());
        confirmBean.setReceiverProvince(addressBean.getAddressProvince());
        confirmBean.setReceiverCity(addressBean.getAddressCity());
        confirmBean.setReceiverRegion(addressBean.getAddressArea());
        confirmBean.setOrderAddress(addressBean.getAddressDetail());
        orderConfirmChooseAddress.setVisibility(View.GONE);
        presenter.getPostage(confirmBean);
    }

    @Override
    public void loadPostage(PostageBean postageBean) {
        DecimalFormat df = new DecimalFormat("0.00");//格式化，区小数后两位

        totalMoney = postageBean.getTotal() - postageBean.getFeight();
        confirmBean.setFreightAmount(postageBean.getFeight());

        orderConfirmTotalYunfei.setText("+￥" + postageBean.getFeight());
        if (postageBean.getIsPinkage() == 0) {
            orderConfirmDeliveryTxt2.setText("包邮");
        } else {
            orderConfirmDeliveryTxt2.setText("￥" + postageBean.getFeight());
        }
        orderConfirmCount.setText(postageBean.getQuantity() + "");
        orderConfirmGoodsCount.setText("共" + postageBean.getQuantity() + "件");
        orderConfirmXiaoji.setText("￥" + df.format(postageBean.getTotal()));
        orderConfirmTotalPrice.setText("￥" + df.format(totalMoney));
        orderConfirmFinalPrice.setText(df.format(postageBean.getTotal() - couponMoney) + "");
    }

    @Override
    public void loadFinish() {
        orderConfirmCoupon.setEnabled(true);
    }

    @Override
    public OrderConfirmView createView() {
        return this;
    }

    @Override
    public OrderConfirmPresneter createPresenter() {
        return new OrderConfirmPresneter(this);
    }
}

package com.example.local_order_confirm;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.LocalOrderBean;
import com.example.bean.RedPackageBean;
import com.example.bean.ShippingAddressBean;
import com.example.local_order_confirm.adapter.LocalOrderConfirmAdapter;
import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

@Route(path = "/module_local/LocalOrderConfirmActivity")
public class LocalOrderConfirmActivity extends BaseActivity<LocalOrderConfirmView, LocalOrderConfirmPresenter> implements LocalOrderConfirmView {
    @BindView(R2.id.include_back2)
    ImageView includeBack2;
    @BindView(R2.id.include_title2)
    TextView includeTitle2;
    @BindView(R2.id.local_order_confirm_name)
    TextView localOrderConfirmName;
    @BindView(R2.id.local_order_confirm_phone)
    TextView localOrderConfirmPhone;
    @BindView(R2.id.local_order_confirm_detail)
    TextView localOrderConfirmDetail;
    @BindView(R2.id.local_order_confirm_add)
    TextView localOrderConfirmAdd;
    @BindView(R2.id.local_order_confirm_time)
    TextView localOrderConfirmTime;
    @BindView(R2.id.local_order_confirm_type)
    TextView localOrderConfirmType;
    @BindView(R2.id.local_order_confirm_choose_pay)
    LinearLayout localOrderConfirmChoosePay;
    @BindView(R2.id.local_order_confirm_shop)
    TextView localOrderConfirmShop;
    @BindView(R2.id.local_order_confirm_rv)
    RecyclerView mRv;
    @BindView(R2.id.local_order_confirm_peisong)
    TextView localOrderConfirmPeisong;
    @BindView(R2.id.local_order_confirm_peisongfei)
    TextView localOrderConfirmPeisongfei;
    @BindView(R2.id.local_order_confirm_reduce_money)
    TextView localOrderConfirmReduceMoney;
    @BindView(R2.id.local_order_confirm_total_money)
    TextView localOrderConfirmTotalMoney;
    @BindView(R2.id.local_order_confirm_final_money)
    TextView localOrderConfirmFinalMoney;
    @BindView(R2.id.local_order_confirm_btn)
    TextView localOrderConfirmBtn;
    @BindView(R2.id.local_order_confirm_rela)
    RelativeLayout mRela;
    @BindView(R2.id.local_order_confirm_coupon_money)
    LinearLayout mCoupon;
    @BindView(R2.id.local_order_confirm_coupon_money_txt)
    TextView mCouponTxt;
    @BindView(R2.id.local_order_confirm_songtype)
    LinearLayout mPeisongType;

    @Autowired(name = "bean")
    LocalOrderBean bean;

    private boolean isWechat = false;
    private boolean hasAddress = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_order_confirm;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        includeTitle2.setText("订单确认");

        localOrderConfirmTotalMoney.setText("￥" + bean.getTotalMoney());
        localOrderConfirmFinalMoney.setText("￥" + bean.getTotalMoney());
        localOrderConfirmReduceMoney.setText("-￥" + (TextUtils.isEmpty(bean.getSellerManJian()) ? "0" : bean.getSellerManJian()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        mRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_10)));
        presenter.initRv(bean.getLocalOrderItemList());
        localOrderConfirmShop.setText(bean.getSellerName());

        presenter.getAddress();
        presenter.getCouponList();
    }

    @Override
    public void initClick() {
        includeBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        localOrderConfirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.chooseAddress();
            }
        });

        mRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.chooseAddress();
            }
        });

        localOrderConfirmChoosePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isWechat) {
                    isWechat = false;
                    localOrderConfirmType.setText("支付宝");
                } else {
                    isWechat = true;
                    localOrderConfirmType.setText("微信");
                }
            }
        });

        localOrderConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasAddress) {
                    localOrderConfirmBtn.setEnabled(false);
                    presenter.toPay(isWechat, bean);
                } else {
                    Toast.makeText(LocalOrderConfirmActivity.this, "请添加收货地址", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mPeisongType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.songTypePop();
            }
        });

        mCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.couponPop();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            hasAddress = true;
            mRela.setVisibility(View.VISIBLE);
            ShippingAddressBean addressBean = (ShippingAddressBean) data.getSerializableExtra("address");
            localOrderConfirmAdd.setVisibility(View.GONE);
            localOrderConfirmName.setText(addressBean.getAddressName());
            localOrderConfirmPhone.setText(addressBean.getAddressPhone());
            localOrderConfirmDetail.setText(addressBean.getAddressProvince() + addressBean.getAddressCity() + addressBean.getAddressArea() + addressBean.getAddressDetail());

            bean.setUserName(addressBean.getAddressName());
            bean.setUserPhone(addressBean.getAddressPhone());
            bean.setUserAddress(addressBean.getAddressProvince() + addressBean.getAddressCity() + addressBean.getAddressArea() + addressBean.getAddressDetail());
        }
    }

    @Override
    public void loadFinish() {
        localOrderConfirmBtn.setEnabled(true);
    }

    @Override
    public void loadAddress(ShippingAddressBean addressBean) {
        hasAddress = true;
        localOrderConfirmAdd.setVisibility(View.GONE);
        mRela.setVisibility(View.VISIBLE);
        localOrderConfirmName.setText(addressBean.getAddressName());
        localOrderConfirmPhone.setText(addressBean.getAddressPhone());
        localOrderConfirmDetail.setText(addressBean.getAddressProvince() + addressBean.getAddressCity() + addressBean.getAddressArea() + addressBean.getAddressDetail());

        bean.setUserName(addressBean.getAddressName());
        bean.setUserPhone(addressBean.getAddressPhone());
        bean.setUserAddress(addressBean.getAddressProvince() + addressBean.getAddressCity() + addressBean.getAddressArea() + addressBean.getAddressDetail());

    }

    @Override
    public void loadSongType(String string) {
        localOrderConfirmPeisong.setText(string);
        if ("商家配送".equals(string)) {
            bean.setDeliverType("1");
            localOrderConfirmPeisongfei.setText("￥");
        } else {
            bean.setDeliverType("0");
        }
    }

    @Override
    public void loadCoupon(RedPackageBean chooseRedPacgage) {
        mCouponTxt.setText("-￥" + chooseRedPacgage.getMoney());
        localOrderConfirmTotalMoney.setText("￥" + (bean.getTotalMoney() * 1000 - Double.valueOf(chooseRedPacgage.getMoney()) * 1000) / 1000);
        localOrderConfirmFinalMoney.setText("￥" + (bean.getTotalMoney() * 1000 - Double.valueOf(chooseRedPacgage.getMoney()) * 1000) / 1000);
    }

    @Override
    public void noAddress() {
        localOrderConfirmAdd.setVisibility(View.VISIBLE);
        mRela.setVisibility(View.GONE);
        hasAddress = false;
    }

    @Override
    public void loadRv(LocalOrderConfirmAdapter adapter) {
        mRv.setAdapter(adapter);
    }

    @Override
    public LocalOrderConfirmView createView() {
        return this;
    }

    @Override
    public LocalOrderConfirmPresenter createPresenter() {
        return new LocalOrderConfirmPresenter(this);
    }
}

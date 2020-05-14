package com.example.local_pay;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bean.LocalShopBean;
import com.example.bean.UserCouponBean;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class LocalPayActivity extends BaseActivity<LocalPayView, LocalPayPresenter> implements LocalPayView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.local_pay_img)
    ImageView localPayImg;
    @BindView(R2.id.local_pay_name)
    TextView localPayName;
    @BindView(R2.id.local_pay_address)
    TextView localPayAddress;
    @BindView(R2.id.local_pay_type)
    TextView localPayType;
    @BindView(R2.id.local_pay_edit)
    EditText localPayEdit;
    @BindView(R2.id.local_pay_weixin_img)
    ImageView localPayWeixinImg;
    @BindView(R2.id.local_pay_weixin)
    LinearLayout localPayWeixin;
    @BindView(R2.id.local_pay_zfb_img)
    ImageView localPayZfbImg;
    @BindView(R2.id.local_pay_zfb)
    LinearLayout localPayZfb;
    @BindView(R2.id.local_pay_balance_img)
    ImageView localPayBalanceImg;
    @BindView(R2.id.local_pay_balance)
    LinearLayout localPayBalance;
    @BindView(R2.id.local_pay_btn)
    TextView mBtn;
    @BindView(R2.id.local_pay_choose_coupon)
    RelativeLayout mChooseCoupon;
    @BindView(R2.id.local_pay_coupon_money)
    TextView mCouponMoney;
    @BindView(R2.id.local_pay_money)
    TextView mMoney;

    private int payType = 0;
    private LocalShopBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_pay;
    }

    @Override
    public void initData() {
//        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        bean = (LocalShopBean) intent.getSerializableExtra("bean");
        includeTitle.setText("确认支付");
        localPayName.setText(bean.getSeller_shop_name());
        localPayAddress.setText(bean.getSeller_addredd());
        localPayType.setText(bean.getSeller_type());
        if (bean.getSellerpics() != null) {
            String[] split = bean.getSellerpics().split(",");
            Glide.with(this).load(split[0]).into(localPayImg);
        }

        presenter.createOrder(bean);
        presenter.getCoupon(bean);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goBack();
            }
        });

        localPayWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payType = 0;
                changePayType();
            }
        });

        localPayZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payType = 1;
                changePayType();
            }
        });

        localPayBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payType = 2;
                changePayType();
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtn.setEnabled(false);
                LogUtil.e("------------>" + payType);
                presenter.commit(mMoney.getText().toString(), payType, bean.getId());
            }
        });

        mChooseCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.choose(localPayEdit.getText().toString());
            }
        });

        localPayEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    if ("00".equals(s.toString())) {
                        localPayEdit.setText("0");
                        localPayEdit.setSelection(localPayEdit.getText().length());
                    } else if (".".equals(s.toString())) {
                        localPayEdit.setText("0.");
                        localPayEdit.setSelection(localPayEdit.getText().length());
                    } else if (s.toString().startsWith("01") || s.toString().startsWith("02") || s.toString().startsWith("03") || s.toString().startsWith("04") || s.toString().startsWith("05")
                            || s.toString().startsWith("06") || s.toString().startsWith("07") || s.toString().startsWith("08") || s.toString().startsWith("09")) {
                        localPayEdit.setText(s.toString().split("0")[1]);
                        localPayEdit.setSelection(localPayEdit.getText().length());
                    }
                    presenter.update(s.toString());
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        presenter.goBack();
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if (CommonResource.WXPAY_SUCCESS_LOCAL.equals(eventBusBean.getMsg())) {
            presenter.jumpToAssess();
        }
    }

    @Override
    public void chooseFinish(UserCouponBean coupon) {
        if (coupon != null) {
            if (coupon.getMinPoint() == 0) {
                mCouponMoney.setText("立减" + coupon.getAmount() + "元");
            } else {
                mCouponMoney.setText("满" + coupon.getMinPoint() + "减" + coupon.getAmount());
            }
        }
    }

    @Override
    public void callBack() {
        mBtn.setEnabled(true);
    }

    @Override
    public void updateMoney(String money) {
        if (Double.valueOf(money) < 0) {
            mMoney.setText("0.1");
        } else {
            mMoney.setText(money);
        }
    }

    private void changePayType() {
        localPayWeixinImg.setImageResource(payType == 0 ? R.drawable.icon_xuanzhong : R.drawable.icon_weixuanzhong);
        localPayZfbImg.setImageResource(payType == 1 ? R.drawable.icon_xuanzhong : R.drawable.icon_weixuanzhong);
        localPayBalanceImg.setImageResource(payType == 2 ? R.drawable.icon_xuanzhong : R.drawable.icon_weixuanzhong);
    }

    @Override
    public LocalPayView createView() {
        return this;
    }

    @Override
    public LocalPayPresenter createPresenter() {
        return new LocalPayPresenter(this);
    }
}

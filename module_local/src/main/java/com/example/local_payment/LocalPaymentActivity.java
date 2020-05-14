package com.example.local_payment;

import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.LocalOrderBean;
import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.SPUtil;

import butterknife.BindView;

@Route(path = "/module_local/LocalPaymentActivity")
public class LocalPaymentActivity extends BaseActivity<LocalPaymentView, LocalPaymentPresenter> implements LocalPaymentView {
    @BindView(R2.id.local_payment_back)
    ImageView localPaymentBack;
    @BindView(R2.id.local_payment_temp1)
    RelativeLayout localPaymentTemp1;
    @BindView(R2.id.local_payment_money)
    TextView localPaymentMoney;
    @BindView(R2.id.local_payment_weixin_img)
    ImageView localPaymentWeixinImg;
    @BindView(R2.id.local_payment_weixin)
    LinearLayout localPaymentWeixin;
    @BindView(R2.id.local_payment_zfb_img)
    ImageView localPaymentZfbImg;
    @BindView(R2.id.local_payment_zfb)
    LinearLayout localPaymentZfb;
    @BindView(R2.id.local_payment_btn)
    TextView localPaymentBtn;

    @Autowired(name = "bean")
    LocalOrderBean bean;

    private boolean isWeChat = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_payment;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        localPaymentMoney.setText("￥" + bean.getTotalMoney());
    }

    @Override
    public void initClick() {
        localPaymentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goBack();
            }
        });

        localPaymentWeixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = true;
                changePayType();
            }
        });

        localPaymentZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = false;
                changePayType();
            }
        });

        localPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localPaymentBtn.setEnabled(false);

                presenter.pay(isWeChat, bean);

            }
        });
    }

    private void changePayType() {
        localPaymentWeixinImg.setImageResource(isWeChat ? com.example.user_store.R.drawable.icon_xuanzhong : com.example.user_store.R.drawable.icon_weixuanzhong);
        localPaymentZfbImg.setImageResource(isWeChat ? com.example.user_store.R.drawable.icon_weixuanzhong : com.example.user_store.R.drawable.icon_xuanzhong);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        presenter.goBack();
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ("13".equals(SPUtil.getStringValue("wxpay"))) {
            SPUtil.addParm("wxpay", "");
            finish();
        }
    }

    @Override
    public void callBack() {
        localPaymentBtn.setEnabled(true);
    }

    @Override
    public LocalPaymentView createView() {
        return this;
    }

    @Override
    public LocalPaymentPresenter createPresenter() {
        return new LocalPaymentPresenter(this);
    }
}

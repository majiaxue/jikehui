package com.example.up_order_confirm;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.bean.OrderConfirmBean;
import com.example.bean.ShippingAddressBean;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

@Route(path = "/mine/upOrderConfirm")
public class UpOrderConfirmActivity extends BaseActivity<UpOrderConfirmView, UpOrderConfirmPresenter> implements UpOrderConfirmView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.up_order_name)
    TextView upOrderName;
    @BindView(R2.id.up_order_phone)
    TextView upOrderPhone;
    @BindView(R2.id.up_order_address_detail)
    TextView upOrderAddressDetail;
    @BindView(R2.id.up_order_choose_address)
    TextView upOrderChooseAddress;
    @BindView(R2.id.up_order_rela)
    RelativeLayout upOrderRela;
    @BindView(R2.id.up_order_img)
    ImageView upOrderImg;
    @BindView(R2.id.up_order_goods_name)
    TextView upOrderGoodsName;
    @BindView(R2.id.up_order_attr)
    TextView upOrderAttr;
    @BindView(R2.id.up_order_price)
    TextView upOrderPrice;
    @BindView(R2.id.up_order_wx_img)
    ImageView upOrderWxImg;
    @BindView(R2.id.up_order_wx)
    LinearLayout upOrderWx;
    @BindView(R2.id.up_order_zfb_img)
    ImageView upOrderZfbImg;
    @BindView(R2.id.up_order_zfb)
    LinearLayout upOrderZfb;
    @BindView(R2.id.up_order_total)
    TextView upOrderTotal;
    @BindView(R2.id.up_order_btn)
    TextView upOrderBtn;

    @Autowired(name = "bean")
    OrderConfirmBean confirmBean;
    @Autowired(name = "name")
    String name;
    @Autowired(name = "levelId")
    String levelId;

    private boolean isWeChat = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_up_order_confirm;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        EventBus.getDefault().register(this);
        includeTitle.setText("确认订单");

        if (confirmBean != null) {
            Glide.with(this).load(confirmBean.getPic()).into(upOrderImg);
            upOrderGoodsName.setText(confirmBean.getProductName());
            upOrderAttr.setText(confirmBean.getProductAttr() == null ? "" : confirmBean.getProductAttr());
            upOrderPrice.setText("￥" + confirmBean.getPrice());
            upOrderTotal.setText(confirmBean.getPrice() + "");
        }
        presenter.getAddress();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goBack();
            }
        });

        upOrderRela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShippingAddress();
            }
        });

        upOrderWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = true;
                upOrderWxImg.setImageResource(R.drawable.icon_xuanzhong);
                upOrderZfbImg.setImageResource(R.drawable.icon_weixuanzhong);
            }
        });

        upOrderZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWeChat = false;
                upOrderWxImg.setImageResource(R.drawable.icon_weixuanzhong);
                upOrderZfbImg.setImageResource(R.drawable.icon_xuanzhong);
            }
        });

        upOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upOrderBtn.setEnabled(false);
                presenter.commit(isWeChat, confirmBean, name, levelId);

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if (CommonResource.WXPAY_CANCEL.equals(eventBusBean.getMsg())) {
            presenter.removeOrder();
        } else if (CommonResource.WXPAY_SUCCESS.equals(eventBusBean.getMsg())) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            ShippingAddressBean addressBean = (ShippingAddressBean) data.getSerializableExtra("address");
            presenter.addressBean = addressBean;
            presenter.isCan = true;
            loadAddress(addressBean);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        presenter.goBack();
        return false;
    }

    @Override
    public void callBack() {
        upOrderBtn.setEnabled(true);
    }

    @Override
    public void loadAddress(ShippingAddressBean addressBean) {
        upOrderChooseAddress.setVisibility(View.GONE);
        upOrderName.setText(addressBean.getAddressName());
        upOrderPhone.setText(addressBean.getAddressPhone());
        upOrderAddressDetail.setText(addressBean.getAddressProvince() + addressBean.getAddressCity() + addressBean.getAddressArea() + addressBean.getAddressDetail());

        confirmBean.setReceiverName(addressBean.getAddressName());
        confirmBean.setReceiverPhone(addressBean.getAddressPhone());
        confirmBean.setReceiverProvince(addressBean.getAddressProvince());
        confirmBean.setReceiverCity(addressBean.getAddressCity());
        confirmBean.setReceiverRegion(addressBean.getAddressArea());
        confirmBean.setOrderAddress(addressBean.getAddressDetail());
    }

    @Override
    public void noAddress() {
        upOrderChooseAddress.setVisibility(View.VISIBLE);

    }

    @Override
    public UpOrderConfirmView createView() {
        return this;
    }

    @Override
    public UpOrderConfirmPresenter createPresenter() {
        return new UpOrderConfirmPresenter(this);
    }
}

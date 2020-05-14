package com.example.shippingaddress;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

/**
 * 收货地址
 */
@Route(path = "/module_user_mine/ShippingAddressActivity")
public class ShippingAddressActivity extends BaseActivity<ShippingAddressView, ShippingAddressPresenter> implements ShippingAddressView {

    @BindView(R2.id.shipping_address_back)
    ImageView shippingAddressBack;
    @BindView(R2.id.wu_di_zhi_icon)
    ImageView wuDiZhiIcon;
    @BindView(R2.id.wu_di_zhi_text)
    TextView wuDiZhiText;
    @BindView(R2.id.shipping_address_rec)
    RecyclerView shippingAddressRec;
    @BindView(R2.id.shipping_address_button)
    TextView shippingAddressButton;

    @Autowired(name = "from")
    String from;


    @Override
    public int getLayoutId() {
        return R.layout.activity_shipping_address;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        presenter.setShippingAddressRec(shippingAddressRec, from);
    }

    @Override
    public void initClick() {
        shippingAddressBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //添加新地址
        shippingAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_mine/AddressActivity").navigation();
            }
        });
    }

    @Override
    public ShippingAddressView createView() {
        return this;
    }

    @Override
    public ShippingAddressPresenter createPresenter() {
        return new ShippingAddressPresenter(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        presenter.setShippingAddressRec(shippingAddressRec, from);
    }
}

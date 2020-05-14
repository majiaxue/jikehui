package com.example.up_order_confirm;

import com.example.bean.ShippingAddressBean;
import com.example.mvp.IView;

public interface UpOrderConfirmView extends IView {
    void loadAddress(ShippingAddressBean bean);

    void noAddress();

    void callBack();
}

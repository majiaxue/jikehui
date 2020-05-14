package com.example.confirm_order;

import com.example.bean.PostageBean;
import com.example.bean.ShippingAddressBean;
import com.example.confirm_order.adapter.ConfirmOrderAdapter;
import com.example.mvp.IView;

public interface ConfirmOrderView extends IView {
    void loadRv(ConfirmOrderAdapter adapter);

    void loadAddress(ShippingAddressBean addressBean);

    void noAddress();

    void loadPostage(double feight, double price, int number);

    void couponAfter(double amount);
}

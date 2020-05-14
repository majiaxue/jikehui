package com.example.local_order_confirm;

import com.example.bean.RedPackageBean;
import com.example.bean.ShippingAddressBean;
import com.example.local_order_confirm.adapter.LocalOrderConfirmAdapter;
import com.example.mvp.IView;

public interface LocalOrderConfirmView extends IView {
    void loadAddress(ShippingAddressBean addressBean);

    void noAddress();

    void loadRv(LocalOrderConfirmAdapter adapter);

    void loadFinish();

    void loadCoupon(RedPackageBean chooseRedPacgage);

    void loadSongType(String string);
}

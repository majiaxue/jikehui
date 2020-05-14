package com.example.local_pay;

import com.example.bean.UserCouponBean;
import com.example.mvp.IView;

public interface LocalPayView extends IView {
    void chooseFinish(UserCouponBean coupon);

    void updateMoney(String money);

    void callBack();
}

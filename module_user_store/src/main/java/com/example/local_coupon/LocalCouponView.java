package com.example.local_coupon;

import com.example.local_coupon.adapter.LocalCouponAdapter;
import com.example.mvp.IView;

public interface LocalCouponView extends IView {
    void loadCoupon(LocalCouponAdapter adapter);
}

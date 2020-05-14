package com.example.coupon_wallet;

import com.example.adapter.CouponWalletAdapter;
import com.example.mvp.IView;

public interface CouponWalletView extends IView {
    void loadRv(CouponWalletAdapter adapter);
}

package com.example.coupon.haveexpired;

import com.example.adapter.CouponWalletAdapter;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public interface HaveExpiredView extends IView {
    void loadRv(CouponWalletAdapter adapter);
}

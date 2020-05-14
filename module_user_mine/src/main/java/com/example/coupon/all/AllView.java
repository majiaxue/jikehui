package com.example.coupon.all;

import com.example.adapter.CouponWalletAdapter;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public interface AllView extends IView {
    void loadRv(CouponWalletAdapter adapter);
}

package com.example.coupon;

import com.example.adapter.BaseVPAdapter;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public interface CouponView extends IView {
    void updateVp(BaseVPAdapter baseVPAdapter);
}

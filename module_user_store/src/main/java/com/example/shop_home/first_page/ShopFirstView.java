package com.example.shop_home.first_page;

import com.example.mvp.IView;
import com.example.shop_home.adapter.FirstCouponAdapter;

public interface ShopFirstView extends IView {
    void loadRv(FirstCouponAdapter adapter);
}

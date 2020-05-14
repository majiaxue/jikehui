package com.example.shop_home;

import com.example.bean.ShopHomeBean;
import com.example.mvp.IView;
import com.example.shop_home.adapter.ShopHomeVPAdapter;

public interface ShopHomeView extends IView {
    void loadVP(ShopHomeVPAdapter adapter);

    void isCollect(String result);

    void initView(ShopHomeBean shopHomeBean);
}

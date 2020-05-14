package com.example.local_shop;

import com.example.bean.BannerBean;
import com.example.local_shop.adapter.LocalNavbarAdapter;
import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.mvp.IView;

import java.util.List;

public interface LocalShopView extends IView {
    void loadBanner(List<BannerBean.RecordsBean> beanList);

    void loadNavbar(LocalNavbarAdapter adapter);

    void loadSeller(LocalSellerAdapter adapter);

    void changed(boolean isDistanceJin, boolean isStarMore);

    void loadFinish();

    void noData();

    void cityName(String cityName);

}

package com.example.local_home;

import com.example.bean.BannerBean;
import com.example.bean.LocalShopBean;
import com.example.bean.LocalShopCommendBean;
import com.example.local_home.adapter.LocalHomeCommendAdapter;
import com.example.local_shop.adapter.LocalNavbarAdapter;
import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.mvp.IView;

import java.util.List;

public interface LocalHomeView extends IView {
    void loadFinish();

    void loadSeller(LocalSellerAdapter adapter);

    void noData();

    void loadNavbar(LocalNavbarAdapter adapter);

    void loadBanner(List<BannerBean.RecordsBean> beanList);

    void loadZhongBanner(List<LocalShopBean> zhongList);

    void loadCommend(LocalShopCommendBean shopCommendBean, LocalHomeCommendAdapter adapter);
}

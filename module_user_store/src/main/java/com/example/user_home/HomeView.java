package com.example.user_home;

import android.view.View;

import com.example.bean.BannerBean;
import com.example.mvp.IView;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_home.adapter.NavBarAdapter;
import com.example.user_home.adapter.SaleHotAdapter;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public interface HomeView extends IView {

    void loadNavBar(NavBarAdapter adapter);

    void loadSaleHot(SaleHotAdapter adapter);

    void loadCommend(CommendAdapter adapter);

    void loadBanner(List<BannerBean.RecordsBean> beanList);

    void lodeMarquee(List<View> views);

    void refreshSuccess();
}

package com.example.buy2up;

import com.example.bean.BannerBean;
import com.example.bean.UserGoodsDetail;
import com.example.mvp.IView;

import java.util.List;

public interface Buy2UpView extends IView {
    void loadUI(UserGoodsDetail bean);

    void loadBanner(List<BannerBean.RecordsBean> banner);
}

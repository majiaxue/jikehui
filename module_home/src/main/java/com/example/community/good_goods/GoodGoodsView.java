package com.example.community.good_goods;

import android.graphics.Bitmap;

import com.example.bean.GoodGoodsBean;
import com.example.community.adapter.GoodGoodsAdapter;
import com.example.mvp.IView;

public interface GoodGoodsView extends IView {

    void loadContent(GoodGoodsAdapter adapter);

    void loadFinish();

    void loadQR(Bitmap bitmap);

    void loadShareInfo(GoodGoodsBean.NetBean.ItemDataBean dataBean);
}

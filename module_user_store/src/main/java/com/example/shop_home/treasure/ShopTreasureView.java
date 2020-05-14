package com.example.shop_home.treasure;

import com.example.mvp.IView;
import com.example.type_detail.adapter.TypeDetailLstAdapter;
import com.example.type_detail.adapter.TypeDetailWaterfallAdapter;

public interface ShopTreasureView extends IView {

    void loadLstRv(TypeDetailLstAdapter adapter, int flag);

    void loadWaterfallRv(TypeDetailWaterfallAdapter adapter, int flag);

    void updateTitle(boolean salesVolume, boolean price, boolean credit);

    void loadFinish();
}

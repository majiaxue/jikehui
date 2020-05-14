package com.example.local_list;

import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.mvp.IView;

public interface LocalListView extends IView {
    void loadFinish();

    void loadSeller(LocalSellerAdapter adapter);

    void noData();

    void changed(boolean isDistanceJin, boolean isStarMore);
}

package com.example.local_detail.local_goods;

import com.example.local_detail.adapter.LocalDetailGoodsAdapter;
import com.example.mvp.IView;

public interface LocalGoodsView extends IView {
    void loadGoods(LocalDetailGoodsAdapter adapter);
}

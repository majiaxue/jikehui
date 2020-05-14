package com.example.goodscollection;

import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.goodscollection.adapter.GoodsCollectionRecAdapter;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public interface GoodsCollectionView extends IView {

    void isCompile(boolean isCompile);

    void isCheckAll(boolean isCheckAll);

    void loadUI(GoodsCollectionRecAdapter adapter);

    void empty(boolean isEmpty);

    void loadCommend(BaseRecStaggeredAdapter adapter);
}

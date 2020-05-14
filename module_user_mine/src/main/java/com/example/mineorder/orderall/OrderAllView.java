package com.example.mineorder.orderall;

import com.example.mineorder.adapter.MineOrderParentAdapter;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public interface OrderAllView extends IView {
    void load(MineOrderParentAdapter mineOrderParentAdapter);
}

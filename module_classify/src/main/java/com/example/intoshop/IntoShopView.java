package com.example.intoshop;

import com.example.intoshop.adapter.IntoShopVPAdapter;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/21
 * Describe:
 */
public interface IntoShopView extends IView {
    void updateVp(IntoShopVPAdapter intoShopVPAdapter);
}

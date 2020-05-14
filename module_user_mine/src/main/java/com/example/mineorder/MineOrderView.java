package com.example.mineorder;

import com.example.adapter.BaseVPAdapter;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public interface MineOrderView extends IView {
    void updateVp(BaseVPAdapter baseVPAdapter);
}

package com.example.mineorder.stayappraise;

import com.example.mineorder.stayappraise.adapter.StayAppraiseParentAdapter;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public interface StayAppraiseView extends IView {
    void load(StayAppraiseParentAdapter stayAppraiseParentAdapter);
}

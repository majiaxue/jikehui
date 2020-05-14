package com.example.superbrand;

import com.example.mvp.IView;
import com.example.superbrand.adapter.SuperBrandRecAdapter;

/**
 * Created by cuihaohao on 2019/6/5
 * Describe:
 */
public interface SuperBrandView extends IView {
    void loadAdapter(SuperBrandRecAdapter superBrandRecAdapter);
}

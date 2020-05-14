package com.example.freecharge;

import com.example.freecharge.adapter.FreeChargeAdapter;
import com.example.freecharge.adapter.FreeChargeLookAdapter;
import com.example.mvp.IView;

public interface FreeChargeView extends IView {
    void noGoods(boolean noGoods);

    void load(FreeChargeAdapter freeChargeAdapter);

    void load(FreeChargeLookAdapter freeChargeLookAdapter);
}

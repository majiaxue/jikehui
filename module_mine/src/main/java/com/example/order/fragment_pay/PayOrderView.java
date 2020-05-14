package com.example.order.fragment_pay;

import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.mvp.IView;
import com.example.order.adapter.JDAdapter;
import com.example.order.adapter.RvListAdapter;
import com.example.order.adapter.TBAdapter;

public interface PayOrderView extends IView {
    void loadMineRv(RvListAdapter adapter);

    void loadJD(JDAdapter adapter);

    void loadTB(TBAdapter adapter);

    void moveTo(int flag);
}

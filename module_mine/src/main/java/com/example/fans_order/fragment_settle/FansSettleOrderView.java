package com.example.fans_order.fragment_settle;

import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.fans_order.adapter.JdFansAdapter;
import com.example.fans_order.adapter.TbFansAdapter;
import com.example.mvp.IView;
import com.example.order.adapter.RvListAdapter;

public interface FansSettleOrderView extends IView {
    void loadFansRv(FansOrderRvAdapter adapter);

    void loadSuccess();

    void loadTb(TbFansAdapter adapter);

    void moveTo(int flag);

    void loadJd(JdFansAdapter adapter);
}

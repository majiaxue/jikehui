package com.example.local_order;

import com.example.local_order.adapter.LocalOrderAdapter;
import com.example.local_order.adapter.LocalOrderNavbarAdapter;
import com.example.local_order.adapter.LocalTuiKuanAdapter;
import com.example.mvp.IView;

public interface LocalOrderView extends IView {
    void loadNavbar(LocalOrderNavbarAdapter adapter);

    void changeType(int position);

    void loadRv(LocalOrderAdapter adapter);

    void loadFinish();

    void loadTuiKuanRv(LocalTuiKuanAdapter adapter);
}

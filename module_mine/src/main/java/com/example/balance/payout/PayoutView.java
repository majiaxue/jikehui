package com.example.balance.payout;

import com.example.balance.adapter.IncomeAdapter;
import com.example.mvp.IView;

public interface PayoutView extends IView {

    void loadRv(IncomeAdapter adapter);

    void loadFinish();
}

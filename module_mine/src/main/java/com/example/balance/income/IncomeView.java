package com.example.balance.income;

import com.example.balance.adapter.IncomeAdapter;
import com.example.mvp.IView;

public interface IncomeView extends IView {

    void loadRv(IncomeAdapter adapter);

    void loadFinish();
}

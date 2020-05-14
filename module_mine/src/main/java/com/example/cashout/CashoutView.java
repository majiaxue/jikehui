package com.example.cashout;

import com.example.mvp.IView;

public interface CashoutView extends IView {

    void loadBalance(String balance);

    void loadInfo(String name, String aliAcount);
}

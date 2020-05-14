package com.example.points_mingxi.cashout_record;

import com.example.mvp.IView;
import com.example.points_mingxi.adapter.CashoutRecordAdapter;

public interface CashoutRecordView extends IView {
    void loadRv(CashoutRecordAdapter adapter);
}

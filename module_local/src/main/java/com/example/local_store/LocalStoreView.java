package com.example.local_store;

import com.example.bean.LocalStoreBean;
import com.example.local_store.ShoppingRight.SortAdapter;
import com.example.mvp.IView;

import java.util.List;

public interface LocalStoreView extends IView {

    void loadData(List<LocalStoreBean> localStoreBeans);

    void upMoney(double money, int size);
}

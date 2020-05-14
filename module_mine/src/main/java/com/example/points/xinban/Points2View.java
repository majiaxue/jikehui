package com.example.points.xinban;

import com.example.mvp.IView;
import com.example.points.xinban.adapter.Points2Adapter;
import com.example.bean.MingXiBean;

public interface Points2View extends IView {

    void loadRv(Points2Adapter adapter);

    void loadData(MingXiBean recordBeans);
}

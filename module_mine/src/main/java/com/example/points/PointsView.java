package com.example.points;

import com.example.bean.MyPointsBean;
import com.example.mvp.IView;

public interface PointsView extends IView {
    void loadData(MyPointsBean pointsBean);
}

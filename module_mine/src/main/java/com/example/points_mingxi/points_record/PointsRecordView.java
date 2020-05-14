package com.example.points_mingxi.points_record;

import com.example.mvp.IView;
import com.example.points_mingxi.adapter.PointsRecordAdapter;

public interface PointsRecordView extends IView {
    void loadRv(PointsRecordAdapter adapter);
}

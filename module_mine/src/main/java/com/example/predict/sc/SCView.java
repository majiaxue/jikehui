package com.example.predict.sc;

import com.example.bean.PredictBean;
import com.example.mvp.IView;

public interface SCView extends IView {
    void loadUI(PredictBean predictBean);

    void loadUI();
}

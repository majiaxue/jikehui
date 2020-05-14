package com.example.predict.tb;

import com.example.bean.PredictBean;
import com.example.mvp.IView;

public interface TBView extends IView {
    void loadUI(PredictBean predictBean);

    void loadUI();
}

package com.example.predict.jd;

import com.example.bean.PredictBean;
import com.example.mvp.IView;

public interface JDView extends IView {
    void loadUI(PredictBean predictBean);

    void loadUI();
}

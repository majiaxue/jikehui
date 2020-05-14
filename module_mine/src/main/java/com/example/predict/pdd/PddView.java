package com.example.predict.pdd;

import com.example.bean.PredictBean;
import com.example.mvp.IView;

public interface PddView extends IView {
    void loadUI(PredictBean predictBean);

    void loadUI();
}

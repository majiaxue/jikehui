package com.example.operator_gain;

import android.support.v4.view.PagerAdapter;

import com.example.mvp.IView;
import com.example.operator_gain.adapter.OperatorGainBottomAdapter;

public interface OperatorGainView extends IView {
    void loadVp(PagerAdapter adapter);

    void loadQuanyi(OperatorGainBottomAdapter adapter);
}

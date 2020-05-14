package com.example.operator;

import android.support.v4.view.PagerAdapter;

import com.example.mvp.IView;
import com.example.operator.adapter.YysFactorAdapter;
import com.example.operator.adapter.YysQuanyiAdapter;

public interface OperatorView extends IView {

    void loadQuanyi(YysQuanyiAdapter adapter);

    void loadVp(PagerAdapter adapter);

    void loadFactor(String s);
}

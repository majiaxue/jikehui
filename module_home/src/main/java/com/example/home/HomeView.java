package com.example.home;

import android.view.View;

import com.example.mvp.IView;

import java.util.List;

public interface HomeView extends IView {
    void lodeMarquee(List<View> views);

    void refreshSuccess();
}

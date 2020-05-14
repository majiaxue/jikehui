package com.example.superbrand.rests;

import com.example.mvp.IView;
import com.example.superbrand.adapter.RestsAdapter;

public interface RestsView extends IView {
    void loadAdapter(RestsAdapter restsAdapter);

    void refreshSuccess();
}

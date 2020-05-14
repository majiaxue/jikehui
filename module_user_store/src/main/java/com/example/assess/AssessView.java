package com.example.assess;

import com.example.assess.adapter.AssessAdapter;
import com.example.assess.adapter.AssessTitleAdapter;
import com.example.mvp.IView;

public interface AssessView extends IView {
    void loadTitle(AssessTitleAdapter adapter);

    void loadAssess(AssessAdapter adapter);
}

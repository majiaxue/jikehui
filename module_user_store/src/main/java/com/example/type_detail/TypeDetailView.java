package com.example.type_detail;

import com.example.mvp.IView;
import com.example.type_detail.adapter.TypeDetailLstAdapter;
import com.example.type_detail.adapter.TypeDetailWaterfallAdapter;

public interface TypeDetailView extends IView {
    void loadLstRv(TypeDetailLstAdapter adapter);

    void loadWaterfallRv(TypeDetailWaterfallAdapter adapter);

    void updateTitle(boolean salesVolume, boolean price, boolean credit);

    void refreshSuccess();
}

package com.example.local_detail;

import com.example.adapter.BaseVPAdapter;
import com.example.local_shop.adapter.ManJianAdapter;
import com.example.mvp.IView;

public interface LocalDetailView extends IView {
    void updateVP(BaseVPAdapter vpAdapter);

    void loadManJian(ManJianAdapter adapter);
}

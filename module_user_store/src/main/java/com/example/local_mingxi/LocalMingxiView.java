package com.example.local_mingxi;

import com.example.local_mingxi.adapter.LocalMingxiAdapter;
import com.example.mvp.IView;

public interface LocalMingxiView extends IView {
    void loadRv(LocalMingxiAdapter adapter);
}

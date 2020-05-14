package com.example.message_center;

import com.example.message_center.adapter.MessageCenterAdapter;
import com.example.mvp.IView;

public interface MessageCenterView extends IView {
    void loadRv(MessageCenterAdapter adapter);

    void loadFinish();
}

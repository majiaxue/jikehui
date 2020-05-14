package com.example.message_list;

import com.example.message_list.adapter.MessageListAdapter;
import com.example.mvp.IView;

public interface MessageListView extends IView {

    void loadUI(MessageListAdapter adapter);
}

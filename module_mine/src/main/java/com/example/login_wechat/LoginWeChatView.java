package com.example.login_wechat;

import com.example.mvp.IView;

public interface LoginWeChatView extends IView {
    void readed();

    void noRead();

    void getCodeSuccess();
}

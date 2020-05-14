package com.example.register;

import com.example.mvp.IView;

public interface RegisterView extends IView {
    void readed();

    void noRead();

    void getCodeSuccess();
}

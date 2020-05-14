package com.example.login;

import com.example.mvp.IView;

public interface LoginView extends IView {

    void getCodeSuccess();

    void getCodeFail();
}

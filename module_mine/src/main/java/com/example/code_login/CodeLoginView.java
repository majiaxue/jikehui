package com.example.code_login;

import com.example.mvp.IView;

public interface CodeLoginView extends IView {

    void getCodeSuccess();

    void agreeAgreement();

    void disagreeAgreement();
}

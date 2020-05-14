package com.example.mvp;


public interface BaseMVP<V extends IView, P extends BasePresenter> {
    V createView();

    P createPresenter();
}

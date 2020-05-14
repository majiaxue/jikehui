package com.example.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends IView, P extends BasePresenter> extends Fragment implements BaseMVP<V, P> {
    protected P presenter;
    protected Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.registerView(createView());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initClick();
    }

    public abstract int getLayoutId();

    public abstract void initData();

    public abstract void initClick();

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null) {
            presenter.destroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}

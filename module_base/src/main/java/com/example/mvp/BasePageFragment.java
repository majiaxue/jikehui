package com.example.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by cuihaohao on 2019/6/16
 * Describe:
 */
public abstract class BasePageFragment<V extends IView, P extends BasePresenter> extends Fragment implements BaseMVP<V, P> {
    protected P presenter;
    protected Unbinder unbinder;
    protected boolean isViewInitiated;
    protected boolean isVisibleToUser;
    protected boolean isDataInitiated;

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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    //查看这个fragment的可见状态
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    public boolean prepareFetchData() {
        return prepareFetchData(false);
    }

    public boolean prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            fetchData();
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    public abstract int getLayoutId();

    public abstract void initData();

    public abstract void initClick();

    //在这个方法中写网络请求
    public abstract void fetchData();

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

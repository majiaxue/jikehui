package com.example.superbrand.rests;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.superbrand.adapter.RestsAdapter;
import com.example.utils.ProcessDialogUtil;
import com.kongzue.dialog.v3.WaitDialog;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class RestsFragment extends BaseFragment<RestsView, RestsPresenter> implements RestsView {

    @BindView(R2.id.rests_rec)
    RecyclerView restsRec;
    @BindView(R2.id.rests_smart_refresh)
    SmartRefreshLayout restsSmartRefresh;

    private int index;
    private int page = 1;

    public static RestsFragment newInstance(int index) {
        RestsFragment fragment = new RestsFragment();
        Bundle args = new Bundle();
        args.putInt("Index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rests;
    }

    @Override
    public void initData() {
        index = getArguments().getInt("Index");
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        restsRec.setLayoutManager(linearLayoutManager);
//        presenter.initList(index);
        ProcessDialogUtil.showProcessDialog(getContext());
//        WaitDialog.show((AppCompatActivity)getActivity(),null);

        presenter.initList(page, index);

        restsSmartRefresh.setRefreshHeader(new MaterialHeader(getContext()));
        restsSmartRefresh.setRefreshFooter(new ClassicsFooter(getContext()));

        //设置上拉刷新下拉加载
        restsSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//                restsRec.setLayoutManager(linearLayoutManager);
                presenter.initList(page, index);

            }
        });
        restsSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//                restsRec.setLayoutManager(linearLayoutManager);
                presenter.initList(page, index);
            }
        });

    }

    @Override
    public void refreshSuccess() {
        restsSmartRefresh.finishLoadMore();
        restsSmartRefresh.finishRefresh();
    }

    @Override
    public void initClick() {

    }

    @Override
    public RestsView createView() {
        return this;
    }

    @Override
    public RestsPresenter createPresenter() {
        return new RestsPresenter(getContext());
    }

    @Override
    public void loadAdapter(RestsAdapter restsAdapter) {
        restsRec.setAdapter(restsAdapter);
    }
}

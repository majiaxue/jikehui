package com.example.balance.income;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.balance.adapter.IncomeAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.SpaceItemDecoration;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class IncomeFragment extends BaseFragment<IncomeView, IncomePresenter> implements IncomeView {
    @BindView(R2.id.income_rv)
    RecyclerView mRv;
    @BindView(R2.id.income_refresh)
    SmartRefreshLayout mRefresh;

    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_income;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(layoutManager);
        mRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getContext().getResources().getDimension(R.dimen.dp_12)));

        //设置 Header 为 官方主题 样式
        mRefresh.setRefreshHeader(new MaterialHeader(getContext()));
        //设置 Footer 为 默认 样式
        mRefresh.setRefreshFooter(new ClassicsFooter(getContext()));

        presenter.loadData(page);
    }

    @Override
    public void initClick() {
        //********************设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.loadData(page);
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadData(page);
            }
        });
    }

    @Override
    public void loadRv(IncomeAdapter adapter) {
        mRv.setAdapter(adapter);
    }

    @Override
    public void loadFinish() {
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
    }

    @Override
    public IncomeView createView() {
        return this;
    }

    @Override
    public IncomePresenter createPresenter() {
        return new IncomePresenter(getContext());
    }
}

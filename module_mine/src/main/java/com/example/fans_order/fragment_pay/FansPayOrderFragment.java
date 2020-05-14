package com.example.fans_order.fragment_pay;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.fans_order.adapter.JdFansAdapter;
import com.example.fans_order.adapter.TbFansAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.order.adapter.RvListAdapter;
import com.example.utils.SpaceItemDecoration;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class FansPayOrderFragment extends BaseFragment<FansPayOrderView, FansPayOrderPresenter> implements FansPayOrderView {
    @BindView(R2.id.fans_order_list_rv)
    RecyclerView orderListRv;
    @BindView(R2.id.fans_order_list_refresh)
    SmartRefreshLayout orderListRefresh;

    private static FansPayOrderFragment fragment;
    private int page = 1;
    private int index = 1;
    private LinearLayoutManager layoutManager;

    public static FansPayOrderFragment getInstance() {
        if (fragment == null) {
            synchronized (FansPayOrderFragment.class) {
                if (fragment == null) {
                    fragment = new FansPayOrderFragment();
                }
            }
        }
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fans_order_list;
    }

    @Override
    public void initData() {
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderListRv.setLayoutManager(layoutManager);
        orderListRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getContext().getResources().getDimension(R.dimen.dp_10)));
        presenter.loadData(page);

        //设置 Header 为 官方主题 样式
        orderListRefresh.setRefreshHeader(new MaterialHeader(getActivity()));
        //设置 Footer 为 默认 样式
        orderListRefresh.setRefreshFooter(new ClassicsFooter(getActivity()));


    }

    @Override
    public void initClick() {
        //********************设置上拉刷新下拉加载
        orderListRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.loadData(page);
            }
        });
        orderListRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadData(page);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

        }
    }

    @Override
    public void loadFansRv(FansOrderRvAdapter adapter) {
        orderListRv.setAdapter(adapter);
    }

    @Override
    public void moveTo(int flag) {
        layoutManager.scrollToPosition(flag);
    }

    @Override
    public void loadTb(TbFansAdapter adapter) {
        orderListRv.setAdapter(adapter);
    }

    @Override
    public void loadJd(JdFansAdapter adapter) {
        orderListRv.setAdapter(adapter);
    }

    @Override
    public void loadSuccess() {
        orderListRefresh.finishRefresh();
        orderListRefresh.finishLoadMore();
    }

    public void setOrigin() {
        page = 1;
        presenter.loadData(page);
    }

    @Override
    public FansPayOrderView createView() {
        return this;
    }

    @Override
    public FansPayOrderPresenter createPresenter() {
        return new FansPayOrderPresenter(getContext());
    }
}

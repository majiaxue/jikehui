package com.example.local_list;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.view.CustomHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

@Route(path = "/user/localList")
public class LocalListActivity extends BaseActivity<LocalListView, LocalListPresenter> implements LocalListView {
    @BindView(R2.id.local_list_back)
    ImageView includeBack;
    @BindView(R2.id.local_list_search)
    TextView mSearch;
    @BindView(R2.id.local_list_text1)
    TextView localListText1;
    @BindView(R2.id.local_list_synthesize_bottom)
    ImageView localListSynthesizeBottom;
    @BindView(R2.id.local_list_synthesize)
    RelativeLayout localListSynthesize;
    @BindView(R2.id.local_list_text2)
    TextView localListText2;
    @BindView(R2.id.local_list_distance_top)
    ImageView localListDistanceTop;
    @BindView(R2.id.local_list_distance_bottom)
    ImageView localListDistanceBottom;
    @BindView(R2.id.local_list_distance)
    RelativeLayout localListDistance;
    @BindView(R2.id.local_list_text3)
    TextView localListText3;
    @BindView(R2.id.local_list_score_top)
    ImageView localListScoreTop;
    @BindView(R2.id.local_list_score_bottom)
    ImageView localListScoreBottom;
    @BindView(R2.id.local_list_score)
    RelativeLayout localListScore;
    @BindView(R2.id.local_list_rv_shop)
    RecyclerView localListRvShop;
    @BindView(R2.id.local_list_refresh)
    SmartRefreshLayout mRefresh;

    private int index = 0;
    private int page = 1;
    @Autowired(name = "type")
    String type;       //店铺类型id
    @Autowired(name = "search")
    String search;     //搜索电批名称
    @Autowired(name = "label")
    int label;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_list;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);

        if (type == null) {
            type = "";
        }
        if (search == null) {
            search = "";
        }
        if (label != 0 && label != 1) {
            label = -1;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        localListRvShop.setLayoutManager(linearLayoutManager);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(this);
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        mRefresh.setRefreshHeader(customHeader);

        mSearch.setText(search);
        presenter.loadData(type, search, "", "", page, label);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        localListSynthesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                presenter.changeSort(index);
            }
        });

        localListDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                presenter.changeSort(index);
            }
        });

        localListScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 2;
                presenter.changeSort(index);
            }
        });

        //设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.refresh(index, page);
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.refresh(index, page);
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToSearch();
            }
        });
    }

    @Override
    public void loadFinish() {
        mRefresh.finishLoadMore();
        mRefresh.finishRefresh();
    }

    @Override
    public void loadSeller(LocalSellerAdapter adapter) {
        localListRvShop.setAdapter(adapter);
    }

    @Override
    public void noData() {
        page--;
    }

    @Override
    public void changed(boolean isDistanceJin, boolean isStarMore) {
        localListText1.setTextColor(Color.parseColor(index == 0 ? "#fd3c15" : "#333333"));
        localListSynthesizeBottom.setImageResource(index == 0 ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk);
        localListText2.setTextColor(Color.parseColor(index == 1 ? "#fd3c15" : "#333333"));
        localListDistanceBottom.setImageResource(index == 1 ? isDistanceJin ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk : R.drawable.khjkjhgjk);
        localListDistanceTop.setImageResource(index == 1 ? isDistanceJin ? R.drawable.ghfgh : R.drawable.gvhgh : R.drawable.ghfgh);
        localListText3.setTextColor(Color.parseColor(index == 2 ? "#fd3c15" : "#333333"));
        localListScoreBottom.setImageResource(index == 2 ? isStarMore ? R.drawable.khjkjhgjk : R.drawable.cgbhdfg : R.drawable.khjkjhgjk);
        localListScoreTop.setImageResource(index == 2 ? isStarMore ? R.drawable.gvhgh : R.drawable.ghfgh : R.drawable.ghfgh);
    }

    @Override
    public LocalListView createView() {
        return this;
    }

    @Override
    public LocalListPresenter createPresenter() {
        return new LocalListPresenter(this);
    }
}

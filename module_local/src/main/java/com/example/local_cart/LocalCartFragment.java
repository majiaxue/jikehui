package com.example.local_cart;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseFragment;
import com.example.user_shopping_cart.adapter.CartParentRecAdapter;
import com.example.view.CustomHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class LocalCartFragment extends BaseFragment<LocalCartView, LocalCartPresenter> implements LocalCartView {
    @BindView(R2.id.local_cart_compile)
    TextView localCartCompile;
    @BindView(R2.id.local_cart_empty)
    LinearLayout localCartEmpty;
    @BindView(R2.id.local_cart_rec)
    RecyclerView localCartRec;
    @BindView(R2.id.local_cart_smart_refresh)
    SmartRefreshLayout localCartSmartRefresh;
    @BindView(R2.id.local_cart_check_all)
    ImageView localCartCheckAll;
    @BindView(R2.id.local_cart_total)
    TextView localCartTotal;
    @BindView(R2.id.local_cart_hide)
    LinearLayout localCartHide;
    @BindView(R2.id.local_cart_close_account_and_delete)
    TextView localCartCloseAccountAndDelete;
    @BindView(R2.id.local_cart_top)
    RelativeLayout mTop;
    @BindView(R2.id.local_cart_bottom)
    RelativeLayout mBottom;
    @BindView(R2.id.local_cart_tobuy)
    TextView mToBuy;

    public boolean compileStatus = true;
    //全选初始状态
    private int totalCount = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_cart;
    }

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        localCartRec.setLayoutManager(linearLayoutManager);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(com.example.user_store.R.color.colorTransparency));
        localCartSmartRefresh.setRefreshHeader(customHeader);
    }

    @Override
    public void initClick() {
        localCartSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                localCartSmartRefresh.finishLoadMore();
            }
        });

        localCartSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                localCartSmartRefresh.finishRefresh();
            }
        });

        //编辑
        localCartCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (compileStatus) {
                    localCartCompile.setText("完成");
                    localCartHide.setVisibility(View.INVISIBLE);
                    localCartCloseAccountAndDelete.setText("删除(" + totalCount + ")");
                    compileStatus = false;
                } else {
                    localCartCompile.setText("编辑");
                    localCartHide.setVisibility(View.VISIBLE);
                    localCartCloseAccountAndDelete.setText("去结算(" + totalCount + ")");
                    compileStatus = true;
                }
            }
        });

        //删除订单和去结算
        localCartCloseAccountAndDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!compileStatus) {
                    presenter.popupDelete();
                } else {
                    presenter.jiesuan();
                }

            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //显示
            mTop.setFocusable(true);
            mTop.setFocusableInTouchMode(true);
            presenter.loadData();
        }
    }

    @Override
    public void deleteSuccess() {
        localCartCompile.setText("编辑");
        localCartHide.setVisibility(View.VISIBLE);
        compileStatus = true;
        localCartCheckAll.setImageResource(com.example.user_store.R.drawable.icon_weixuanzhong);
    }

    @Override
    public void isHide(boolean isHide) {
        if (isHide) {
            localCartRec.setVisibility(View.GONE);
            localCartEmpty.setVisibility(View.VISIBLE);
            localCartCompile.setVisibility(View.GONE);
            localCartCloseAccountAndDelete.setEnabled(false);
            mBottom.setVisibility(View.INVISIBLE);
        } else {
            localCartRec.setVisibility(View.VISIBLE);
            localCartEmpty.setVisibility(View.GONE);
            localCartCompile.setVisibility(View.VISIBLE);
            localCartCloseAccountAndDelete.setEnabled(true);
            mBottom.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void updateCount(int count) {
        totalCount = count;
        if (compileStatus) {
            localCartCloseAccountAndDelete.setText("去结算(" + count + ")");
        } else {
            localCartCloseAccountAndDelete.setText("删除(" + count + ")");
        }
    }

    @Override
    public void loadCartRv(CartParentRecAdapter adapter) {
        localCartRec.setAdapter(adapter);
    }

    @Override
    public void isCheckAll(boolean isCheckAll) {
        if (isCheckAll) {
            localCartCheckAll.setImageResource(com.example.user_store.R.drawable.icon_xuanzhong);
        } else {
            localCartCheckAll.setImageResource(com.example.user_store.R.drawable.icon_weixuanzhong);
        }
    }

    @Override
    public void totalPrice(double price) {
        localCartTotal.setText("" + price);
    }

    @Override
    public void loadSuccess() {
        localCartSmartRefresh.finishRefresh();
        localCartSmartRefresh.finishLoadMore();
    }

    @Override
    public LocalCartView createView() {
        return this;
    }

    @Override
    public LocalCartPresenter createPresenter() {
        return new LocalCartPresenter(getContext());
    }
}

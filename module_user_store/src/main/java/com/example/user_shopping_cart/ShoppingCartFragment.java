package com.example.user_shopping_cart;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.entity.EventBusBean2;
import com.example.mvp.BaseFragment;
import com.example.user_shopping_cart.adapter.CartParentRecAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.ArithUtil;
import com.example.view.CustomHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:多用户商城购物车页面
 */
public class ShoppingCartFragment extends BaseFragment<ShoppingCartView, ShoppingCartPresenter> implements ShoppingCartView {
    @BindView(R2.id.shopping_cart_compile)
    TextView shoppingCartCompile;
    @BindView(R2.id.shopping_cart_empty)
    LinearLayout shoppingCartEmpty;
    @BindView(R2.id.shopping_cart_rec)
    RecyclerView shoppingCartRec;
    @BindView(R2.id.shopping_cart_smart_refresh)
    SmartRefreshLayout shoppingCartSmartRefresh;
    @BindView(R2.id.shopping_cart_check_all)
    ImageView shoppingCartCheckAll;
    @BindView(R2.id.shopping_cart_total)
    TextView shoppingCartTotal;
    @BindView(R2.id.shopping_cart_hide)
    LinearLayout shoppingCartHide;
    @BindView(R2.id.shopping_cart_close_account_and_delete)
    TextView shoppingCartCloseAccountAndDelete;
    @BindView(R2.id.cart_top)
    RelativeLayout mTop;
    @BindView(R2.id.shopping_cart_bottom)
    RelativeLayout mBottom;
    @BindView(R2.id.shopping_cart_tobuy)
    TextView mToBuy;

    public boolean compileStatus = true;
    //全选初始状态
    private boolean isCheckAllParent = false;
    private int totalCount = 0;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_shopping_cart;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        shoppingCartRec.setLayoutManager(linearLayoutManager);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        shoppingCartSmartRefresh.setRefreshHeader(customHeader);

        //编辑
        shoppingCartCompile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (compileStatus) {
                    shoppingCartCompile.setText("完成");
                    shoppingCartHide.setVisibility(View.INVISIBLE);
                    shoppingCartCloseAccountAndDelete.setText("删除(" + totalCount + ")");
                    compileStatus = false;
                    presenter.editOrDelete(compileStatus);
                } else {
                    shoppingCartCompile.setText("编辑");
                    shoppingCartHide.setVisibility(View.VISIBLE);
                    shoppingCartCloseAccountAndDelete.setText("去结算(" + totalCount + ")");
                    compileStatus = true;
                }
            }
        });

        //删除订单和去结算
        shoppingCartCloseAccountAndDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!compileStatus) {
                    presenter.popupDelete();
                } else {
                    presenter.jiesuan();
                }

            }
        });

        //全选
        shoppingCartCheckAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCheckAllParent) {
                    shoppingCartCheckAll.setImageResource(R.drawable.icon_xuanzhong);
                    isCheckAllParent = false;
                } else {
                    shoppingCartCheckAll.setImageResource(R.drawable.icon_weixuanzhong);
                    isCheckAllParent = true;
                }
                presenter.checkAllParent(isCheckAllParent);
            }
        });

    }

    @Override
    public void initClick() {
        shoppingCartSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.setShoppingCartRec();
            }
        });

        mToBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusBean2("toBuy", 1));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if (CommonResource.CART_REFRESH.equals(eventBusBean.getMsg())) {
            mTop.setFocusable(true);
            mTop.setFocusableInTouchMode(true);
            presenter.setShoppingCartRec();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //显示
            mTop.setFocusable(true);
            mTop.setFocusableInTouchMode(true);
            presenter.setShoppingCartRec();
        }
    }

    @Override
    public ShoppingCartView createView() {
        return this;
    }

    @Override
    public void deleteSuccess() {
        shoppingCartCompile.setText("编辑");
        shoppingCartHide.setVisibility(View.VISIBLE);
        compileStatus = true;
        shoppingCartCheckAll.setImageResource(R.drawable.icon_weixuanzhong);
        isCheckAllParent = true;
    }

    @Override
    public ShoppingCartPresenter createPresenter() {
        return new ShoppingCartPresenter(getContext());
    }

    @Override
    public void isHide(boolean isHide) {
        if (isHide) {
            shoppingCartRec.setVisibility(View.GONE);
            shoppingCartEmpty.setVisibility(View.VISIBLE);
            shoppingCartCompile.setVisibility(View.GONE);
            shoppingCartCloseAccountAndDelete.setEnabled(false);
            mBottom.setVisibility(View.GONE);
        } else {
            shoppingCartRec.setVisibility(View.VISIBLE);
            shoppingCartEmpty.setVisibility(View.GONE);
            shoppingCartCompile.setVisibility(View.VISIBLE);
            shoppingCartCloseAccountAndDelete.setEnabled(true);
            mBottom.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void updateCount(int count) {
        totalCount = count;
        if (compileStatus) {
            shoppingCartCloseAccountAndDelete.setText("去结算(" + count + ")");
        } else {
            shoppingCartCloseAccountAndDelete.setText("删除(" + count + ")");
        }
    }

    @Override
    public void loadCartRv(CartParentRecAdapter adapter) {
        shoppingCartRec.setAdapter(adapter);
        presenter.click();
    }

    @Override
    public void isCheckAll(boolean isCheckAll) {
        if (isCheckAll) {
            shoppingCartCheckAll.setImageResource(R.drawable.icon_xuanzhong);
            isCheckAllParent = false;
        } else {
            shoppingCartCheckAll.setImageResource(R.drawable.icon_weixuanzhong);
            isCheckAllParent = true;
        }
    }

    @Override
    public void totalPrice(double price) {
        shoppingCartTotal.setText("" + ArithUtil.exact(price, 2));
    }

    @Override
    public void loadSuccess() {
        shoppingCartSmartRefresh.finishRefresh();
        shoppingCartSmartRefresh.finishLoadMore();
    }
}

package com.example.local_store;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.example.bean.LocalCartBean;
import com.example.bean.LocalShopBean;
import com.example.bean.LocalStoreBean;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.local_store.ShoppingRight.CheckListener;
import com.example.local_store.ShoppingRight.GoodsView;
import com.example.local_store.ShoppingRight.ItemHeaderDecoration;
import com.example.local_store.ShoppingRight.RvListener;
import com.example.local_store.ShoppingRight.ShopOnClickListtener;
import com.example.local_store.ShoppingRight.SortAdapter;
import com.example.local_store.ShoppingRight.SortDetailFragment;
import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseFragmentActivity;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = "/module_local/LocalStoreActivity")
public class LocalStoreActivity extends BaseFragmentActivity<LocalStoreView, LocalStorePresenter> implements LocalStoreView, ShopOnClickListtener, CheckListener {
    @BindView(R2.id.local_store_img)
    ImageView localStoreImg;
    @BindView(R2.id.local_store_back)
    ImageView localStoreBack;
    @BindView(R2.id.local_store_logo)
    ImageView localStoreLogo;
    @BindView(R2.id.local_store_txt1)
    TextView localStoreTxt1;
    @BindView(R2.id.local_store_rv_commend)
    RecyclerView localStoreRvCommend;
    @BindView(R2.id.local_store_rv_left)
    RecyclerView localStoreRvLeft;
    @BindView(R2.id.local_store_total_money)
    TextView localStoreTotalMoney;
    @BindView(R2.id.local_store_btn)
    TextView localStoreBtn;
    @BindView(R2.id.local_store_bottom)
    LinearLayout localStoreBottom;


    @Autowired(name = "bean")
    LocalShopBean bean;
    private LinearLayoutManager leftLayoutManager;

    private int[] startP = new int[2];
    private int[] endP = new int[2];
    private int endWidth;
    private ViewGroup mViewGroup;

    private SortDetailFragment mSortDetailFragment;
    private int targetPosition;//点击左边某一个具体的item的位置
    private boolean isMoved;
    private SortAdapter mSortAdapter;
    private List<List<LocalStoreBean.ListBean>> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_store;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        EventBus.getDefault().register(this);
        SPUtil.addParm(CommonResource.SELLERID, bean.getPigxx_id());
        SPUtil.addParm(CommonResource.SELLERNAME, bean.getSeller_shop_name());
        leftLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        localStoreRvLeft.setLayoutManager(leftLayoutManager);
        localStoreRvLeft.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_10)));

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        localStoreRvCommend.setLayoutManager(layoutManager1);
        localStoreRvCommend.addItemDecoration(new SpaceItemDecoration(0, (int) getResources().getDimension(R.dimen.dp_7), 0, 0));

        mViewGroup = (ViewGroup) getWindow().getDecorView();
        Glide.with(this).load(bean.getSeller_logo()).into(localStoreLogo);
        String sellerpics = bean.getSellerpics();
        if (!TextUtils.isEmpty(sellerpics)) {
            String[] split = sellerpics.split(",");
            Glide.with(this).load(split[0]).into(localStoreImg);
        }

        localStoreTxt1.setText("满" + bean.getMin_point() + "减" + bean.getFull_reduction_amount() + "元");
        presenter.loadData(bean.getPigxx_id());
        presenter.loadCart(bean.getPigxx_id());
    }

    @Override
    public void initClick() {
        localStoreBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        localStoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusBean(CommonResource.SUBMIT_ORDER));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if (CommonResource.UPCART.equals(eventBusBean.getCode())) {
            presenter.upCart(eventBusBean.getMsg());
        } else if (CommonResource.MINUS_GOODS.equals(eventBusBean.getCode())) {
            presenter.cartPop(localStoreBottom);
        }
    }

    @Override
    public void upMoney(double money, int size) {
        localStoreTotalMoney.setText("￥" + money);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        localStoreTotalMoney.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                endPlace(localStoreTotalMoney);
                localStoreTotalMoney.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    //将当前选中的item居中
    public void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = localStoreRvLeft.getChildAt(position - leftLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - localStoreRvLeft.getHeight() / 2);
            localStoreRvLeft.smoothScrollBy(0, y);
        }
    }

    @Override
    public void loadData(List<LocalStoreBean> localStoreBeans) {
        mSortAdapter = new SortAdapter(this, localStoreBeans, new RvListener() {
            @Override
            public void onItemClick(int id, int position) {
                if (mSortDetailFragment != null) {
                    isMoved = true;
                    targetPosition = position;
                    setChecked(position, true);
                }
            }
        }, this);
        localStoreRvLeft.setAdapter(mSortAdapter);
        mSortAdapter.setCheckedPosition(0);

        List<LocalStoreBean.ListBean> rightList = new ArrayList<>();
        for (int i = 0; i < localStoreBeans.size(); i++) {
            LocalStoreBean.ListBean listBean = new LocalStoreBean.ListBean(localStoreBeans.get(i).getShopCategoryName(), true, i + "");
            rightList.add(listBean);
            List<LocalStoreBean.ListBean> temp = new ArrayList<>();
            for (int j = 0; j < localStoreBeans.get(i).getList().size(); j++) {
                localStoreBeans.get(i).getList().get(j).setTag(i + "");
                rightList.add(localStoreBeans.get(i).getList().get(j));
                temp.add(localStoreBeans.get(i).getList().get(j));
            }
            list.add(temp);
        }

        mSortDetailFragment = new SortDetailFragment(rightList, this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.local_store_rv_right, mSortDetailFragment).commit();
        mSortDetailFragment.setListener(new CheckListener() {
            @Override
            public void check(int position, boolean isScroll) {
                setChecked(position, isScroll);
            }
        });

    }

    private void setChecked(int position, boolean isLeft) {
        if (isLeft) {
            mSortAdapter.setCheckedPosition(position);
            //此处的位置需要根据每个分类的集合来进行计算
            int count = 0;
            for (int i = 0; i < position; i++) {
                count += list.get(i).size();
            }
            count += position;
            mSortDetailFragment.setData(count);
            ItemHeaderDecoration.setCurrentTag(String.valueOf(targetPosition));//凡是点击左边，将左边点击的位置作为当前的tag
        } else {
            if (isMoved) {
                isMoved = false;
            } else
                mSortAdapter.setCheckedPosition(position);
            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));//如果是滑动右边联动左边，则按照右边传过来的位置作为tag

        }
        moveToCenter(position);
    }

    @Override
    public LocalStoreView createView() {
        return this;
    }

    @Override
    public LocalStorePresenter createPresenter() {
        return new LocalStorePresenter(this);
    }

    @Override
    public void check(int position, boolean isScroll) {

    }

    @Override
    public void startPlace(View view) {
        //获取增加商品按钮坐标
        view.getLocationOnScreen(startP);
        //生成商品View
        GoodsView goodsView = new GoodsView(this);
        goodsView.setCircleStartPoint(startP[0], startP[1]);
        goodsView.setCircleEndPoint(endP[0] + endWidth / 4, endP[1]);
        //添加View并执行动画
        mViewGroup.addView(goodsView);
        goodsView.startAnimation();
    }

    @Override
    public void endPlace(View view) {
        //获取购物车坐标
        view.getLocationOnScreen(endP);
        endWidth = view.getWidth();
    }

    public void updateCount(List<LocalCartBean.InsideCart> data) {

    }
}

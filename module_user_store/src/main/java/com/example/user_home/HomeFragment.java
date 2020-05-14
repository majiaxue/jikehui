package com.example.user_home;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.BannerBean;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.entity.EventBusBean2;
import com.example.mvp.BaseFragment;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_home.adapter.NavBarAdapter;
import com.example.user_home.adapter.SaleHotAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.LogUtil;
import com.example.utils.RvItemDecoration;
import com.example.utils.SpaceItemDecoration;
import com.example.view.CustomHeader;
import com.example.view.MarqueeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:商城首页
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, NestedScrollView.OnScrollChangeListener {
    @BindView(R2.id.user_home_back)
    ImageView userHomeBack;
    @BindView(R2.id.user_home_search)
    TextView userHomeSearch;
    @BindView(R2.id.user_home_msg_img)
    ImageView userHomeMsgImg;
    @BindView(R2.id.user_home_msg)
    LinearLayout userHomeMsg;
    @BindView(R2.id.user_home_xbanner)
    XBanner userHomeXbanner;
    @BindView(R2.id.user_home_rv_navbar)
    RecyclerView userHomeRvNavbar;
    @BindView(R2.id.user_home_more)
    TextView userHomeMore;
    @BindView(R2.id.user_home_rv_hot)
    RecyclerView userHomeRvHot;
    @BindView(R2.id.user_home_rv_goods)
    RecyclerView userHomeRvGoods;
    @BindView(R2.id.user_home_refresh)
    SmartRefreshLayout userHomeRefresh;
    @BindView(R2.id.user_home_nescroll)
    NestedScrollView userHomeNescroll;
    @BindView(R2.id.user_home_gotop)
    ImageView mGoTop;
    @BindView(R2.id.user_home_marquee)
    MarqueeView userHomeMarquee;

    private int newGoodsIndex = 1;
    private int hotSaleIndex = 1;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_home;
    }

    @Override
    public void initData() {

        //跑马灯
        presenter.setViewSingleLine();
        //导航栏
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        userHomeRvNavbar.setLayoutManager(gridLayoutManager);
        //热销产品
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        userHomeRvHot.addItemDecoration(new SpaceItemDecoration(0, (int) getResources().getDimension(R.dimen.dp_8), 0, 0));
        userHomeRvHot.setLayoutManager(linearLayoutManager);

        //新品推荐
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        userHomeRvGoods.setLayoutManager(staggeredGridLayoutManager);
        userHomeRvGoods.addItemDecoration(new RvItemDecoration((int) getContext().getResources().getDimension(R.dimen.dp_12), (int) getContext().getResources().getDimension(R.dimen.dp_12)));
        presenter.loadData(hotSaleIndex);
        presenter.setXBanner();
        presenter.getNewRecommend(newGoodsIndex);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        userHomeRefresh.setRefreshHeader(customHeader);

        userHomeNescroll.setOnScrollChangeListener(this);
    }

    @Override
    public void initClick() {
        userHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusBean2(CommonResource.USER_BACK, 0));
            }
        });

        userHomeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToSearch();
            }
        });

        userHomeMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/messagecenter").navigation();
            }
        });

        //监听广告 item 的单击事件
        userHomeXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
//                Toast.makeText(getContext(), "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });

        //设置上拉刷新下拉加载
        userHomeRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                newGoodsIndex = 1;
                presenter.getNewRecommend(newGoodsIndex);
            }
        });
        userHomeRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                newGoodsIndex++;
                presenter.getNewRecommend(newGoodsIndex);
            }
        });

        userHomeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_user_store/typeDetail").withBoolean("hotSale", true).navigation();
            }
        });

        mGoTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userHomeNescroll.fullScroll(NestedScrollView.FOCUS_UP);
            }
        });
    }

    @Override
    public void loadNavBar(NavBarAdapter adapter) {
        userHomeRvNavbar.setAdapter(adapter);
    }

    @Override
    public void loadSaleHot(SaleHotAdapter adapter) {
        userHomeRvHot.setAdapter(adapter);
    }

    @Override
    public void loadCommend(CommendAdapter adapter) {
        userHomeRefresh.finishLoadMore();
        userHomeRvGoods.setAdapter(adapter);
        presenter.commendClick();
    }

    @Override
    public void loadBanner(final List<BannerBean.RecordsBean> beanList) {
        userHomeXbanner.setBannerData(beanList);
        userHomeXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                RequestOptions requestOptions = RequestOptions.centerCropTransform();
                Glide.with(getContext()).load(((BannerBean.RecordsBean) model).getXBannerUrl()).apply(requestOptions).transform(new RoundedCorners((int) getContext().getResources().getDimension(R.dimen.dp_10))).into((ImageView) view);
            }
        });

        //banner切换image也切换
//        userHomeXbanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                if (!getActivity().isFinishing()) {
//                    Glide.with(getContext()).load(beanList.get(i).getPicBackUrl()).into(userHomeTopImg);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
    }

    @Override
    public void lodeMarquee(List<View> views) {
        userHomeMarquee.setViews(views);
    }

    @Override
    public void refreshSuccess() {
        userHomeRefresh.finishLoadMore();
        userHomeRefresh.finishRefresh();
    }

    @Override
    public HomeView createView() {
        return this;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getContext());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //隐藏
            userHomeXbanner.stopAutoPlay();
            userHomeMarquee.stopFlipping();

        } else {
            //显示
            userHomeXbanner.startAutoPlay();
            userHomeMarquee.startFlipping();

        }
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int[] ints = new int[2];
        userHomeRvGoods.getLocationOnScreen(ints);
        int y = ints[1];
        if (y <= 0) {
            mGoTop.setVisibility(View.VISIBLE);
        } else {
            mGoTop.setVisibility(View.GONE);
        }
    }
}

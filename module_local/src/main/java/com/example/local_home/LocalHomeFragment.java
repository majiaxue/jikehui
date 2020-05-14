package com.example.local_home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.BannerBean;
import com.example.bean.LocalShopBean;
import com.example.bean.LocalShopCommendBean;
import com.example.bean.UserCouponBean;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.local_home.adapter.LocalHomeCommendAdapter;
import com.example.local_shop.adapter.LocalNavbarAdapter;
import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.location.LocationActivity;
import com.example.module_base.ModuleBaseApplication;
import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.ArithUtil;
import com.example.utils.CitySPUtil;
import com.example.utils.LogUtil;
import com.example.utils.MyLocationListener;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SpaceItemDecoration;
import com.example.view.CustomHeader;
import com.example.view.RatingBarView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class LocalHomeFragment extends BaseFragment<LocalHomeView, LocalHomePresenter> implements LocalHomeView {
    @BindView(R2.id.local_home_city)
    TextView localHomeCity;
    @BindView(R2.id.local_home_choose_city)
    LinearLayout localHomeChooseCity;
    @BindView(R2.id.local_home_msg)
    ImageView localHomeMsg;
    @BindView(R2.id.local_home_search)
    LinearLayout localHomeSearch;
    @BindView(R2.id.local_home_xbanner)
    XBanner localHomeXbanner;
    @BindView(R2.id.local_home_navbar)
    RecyclerView localHomeNavbar;
    @BindView(R2.id.local_home_xiaochi)
    ImageView localHomeXiaochi;
    @BindView(R2.id._local_home_zhong_banner)
    XBanner LocalHomeZhongBanner;
    @BindView(R2.id.local_home_commend_shop)
    TextView localHomeCommendShop;
    @BindView(R2.id.local_home_commend_rv)
    RecyclerView localHomeCommendRv;
    @BindView(R2.id.local_home_rv_shop)
    RecyclerView localHomeRvShop;
    @BindView(R2.id.local_home_refresh)
    SmartRefreshLayout mRefresh;

    private List<LocalShopBean> zhongList;
    private static int REQUESTCODE = 0;
    private boolean isFirst = true;
    private int page = 1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_home;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        if (!TextUtils.isEmpty(CitySPUtil.getStringValue(CommonResource.CITY))) {
            LogUtil.e("城市----------->" + CitySPUtil.getStringValue(CommonResource.CITY));
            localHomeCity.setText(CitySPUtil.getStringValue(CommonResource.CITY));
        } else {
            localHomeCity.setText("选择城市");
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        localHomeNavbar.setLayoutManager(layoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        localHomeRvShop.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        localHomeCommendRv.setLayoutManager(linearLayoutManager1);
        localHomeCommendRv.addItemDecoration(new SpaceItemDecoration(0, (int) getContext().getResources().getDimension(R.dimen.dp_4), 0, 0));

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(com.example.user_store.R.color.colorTransparency));
        mRefresh.setRefreshHeader(customHeader);

        ProcessDialogUtil.showProcessDialog(getContext());

        presenter.initNavbar();
        presenter.loadData(page, MyLocationListener.longitude, MyLocationListener.latitude);
        presenter.getXBanner();
        presenter.isOpenLocation();
        presenter.initCommend(MyLocationListener.longitude, MyLocationListener.latitude, CitySPUtil.getStringValue(CommonResource.CITY));

        if (!TextUtils.isEmpty(CitySPUtil.getStringValue(CommonResource.CITY))) {
            localHomeCity.setText(CitySPUtil.getStringValue(CommonResource.CITY));
        } else if (!TextUtils.isEmpty(MyLocationListener.district)) {
            localHomeCity.setText(MyLocationListener.district);
        } else {
            localHomeCity.setText("选择城市");
        }
        ModuleBaseApplication.isDingWei = true;
    }

    @Override
    public void initClick() {
        localHomeChooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LocationActivity.class);
                intent.putExtra("cityName", localHomeCity.getText().toString());
                startActivityForResult(intent, REQUESTCODE);
//                ARouter.getInstance().build("/module_user_store/LocationActivity").withString("cityName", localHomeCity.getText().toString()).navigation(getActivity(), REQUESTCODE);
            }
        });

        //设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.loadData(page, MyLocationListener.longitude, MyLocationListener.latitude);
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadData(page, MyLocationListener.longitude, MyLocationListener.latitude);
            }
        });

        localHomeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToSearch();
            }
        });

        LocalHomeZhongBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                ARouter.getInstance().build("/module_local/LocalStoreActivity").withSerializable("bean", zhongList.get(position)).navigation();
            }
        });

        localHomeMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/messagecenter").navigation();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if (CommonResource.DINGWEI.equals(eventBusBean.getMsg())) {
            localHomeCity.setText(MyLocationListener.district);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String cityName = data.getStringExtra("cityName");
        if (resultCode == 0) {
            localHomeCity.setText(cityName);
            GeoCoder mCoder = GeoCoder.newInstance();

            mCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                @Override
                public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                    if (null != geoCodeResult && null != geoCodeResult.getLocation()) {
                        if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                            //没有检索到结果
                            LogUtil.e("没检索到结果");
                            return;
                        } else {
                            double latitude = geoCodeResult.getLocation().latitude;
                            double longitude = geoCodeResult.getLocation().longitude;
                            LogUtil.e("-------->纬度：" + latitude + "--------->经度：" + longitude);
                            page = 1;
                            presenter.loadData(page, longitude, latitude);
                        }
                    }
                }

                @Override
                public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                }
            });

            mCoder.geocode(new GeoCodeOption()
                    .city(cityName)
                    .address(cityName));

            mCoder.destroy();
        }
    }

    @Override
    public void loadCommend(LocalShopCommendBean shopCommendBean, LocalHomeCommendAdapter adapter) {
        localHomeCommendShop.setText(shopCommendBean.getSellerName());
        localHomeCommendRv.setAdapter(adapter);
    }

    @Override
    public void loadZhongBanner(List<LocalShopBean> zhongList) {
        this.zhongList = zhongList;
        LocalHomeZhongBanner.setBannerData(R.layout.local_home_xbanner, zhongList);
        LocalHomeZhongBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageView img = view.findViewById(R.id.local_home_xbanner_img);
                TextView name = view.findViewById(R.id.local_home_xbanner_name);
                TextView address = view.findViewById(R.id.local_home_xbanner_address);
                RatingBarView rtb = view.findViewById(R.id.local_home_xbanner_rtb);
                TextView coupon1 = view.findViewById(R.id.local_home_xbanner_coupon1);
                TextView coupon2 = view.findViewById(R.id.local_home_xbanner_coupon2);
                TextView type = view.findViewById(R.id.local_home_xbanner_type);
                TextView space = view.findViewById(R.id.local_home_xbanner_space);

                name.setText(((LocalShopBean) model).getSeller_shop_name());
                address.setText(((LocalShopBean) model).getSeller_addredd());
                rtb.setClickable(false);
                rtb.setStar(((LocalShopBean) model).getStar(), false);
                type.setText(((LocalShopBean) model).getSeller_category_name());

                if (((LocalShopBean) model).getDistance() != null) {
                    Integer integer = Integer.valueOf(((LocalShopBean) model).getDistance().split("\\.")[0]);
                    if (integer >= 1000) {
                        double mi=   ( ArithUtil.div(integer * 1.0, 1000.0, 1))/1000;
                        double exact = ArithUtil.exact(mi, 2);
                        space.setText(exact + "千米");
                    } else {
                        space.setText(integer + "米");
                    }
                }
                List<UserCouponBean> couponList = ((LocalShopBean) model).getCouponList();
                if (couponList.size() > 1) {
                    if (couponList.get(0).getMinPoint() == 0) {
                        coupon1.setText("可用购物金" + couponList.get(0).getAmount() + "元");
                    } else {
                        coupon1.setText("满" + couponList.get(0).getMinPoint() + "减" + couponList.get(0).getAmount());
                    }

                    if (couponList.get(1).getMinPoint() == 0) {
                        coupon2.setText("可用购物金" + couponList.get(1).getAmount() + "元");
                    } else {
                        coupon2.setText("满" + couponList.get(1).getMinPoint() + "减" + couponList.get(1).getAmount());
                    }
                } else if (couponList.size() == 1) {
                    if (couponList.get(0).getMinPoint() == 0) {
                        coupon1.setText("可用购物金" + couponList.get(0).getAmount() + "元");
                    } else {
                        coupon1.setText("满" + couponList.get(0).getMinPoint() + "减" + couponList.get(0).getAmount());
                    }
                    coupon2.setVisibility(View.GONE);
                }

                RequestOptions requestOptions = RequestOptions.centerCropTransform();
                Glide.with(getContext()).load(((LocalShopBean) model).getSeller_logo()).apply(requestOptions).transform(new RoundedCorners((int) getContext().getResources().getDimension(com.example.user_store.R.dimen.dp_10))).into(img);
            }
        });
    }

    @Override
    public void loadSeller(LocalSellerAdapter adapter) {
        localHomeRvShop.setAdapter(adapter);
    }

    @Override
    public void noData() {
        page--;
    }

    @Override
    public void loadFinish() {
        mRefresh.finishLoadMore();
        mRefresh.finishRefresh();
    }

    @Override
    public void loadBanner(List<BannerBean.RecordsBean> beanList) {
        localHomeXbanner.setBannerData(beanList);

        localHomeXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                RequestOptions requestOptions = RequestOptions.centerCropTransform();
                Glide.with(getContext()).load(((BannerBean.RecordsBean) model).getXBannerUrl()).apply(requestOptions).transform(new RoundedCorners((int) getContext().getResources().getDimension(com.example.user_store.R.dimen.dp_10))).into((ImageView) view);
            }
        });
    }

    @Override
    public void loadNavbar(LocalNavbarAdapter adapter) {
        localHomeNavbar.setAdapter(adapter);
    }

    @Override
    public LocalHomeView createView() {
        return this;
    }

    @Override
    public LocalHomePresenter createPresenter() {
        return new LocalHomePresenter(getContext());
    }
}

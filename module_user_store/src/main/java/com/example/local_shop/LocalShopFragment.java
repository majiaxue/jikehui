package com.example.local_shop;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.example.common.CommonResource;
import com.example.local_shop.adapter.LocalNavbarAdapter;
import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.location.LocationActivity;
import com.example.module_base.ModuleBaseApplication;
import com.example.mvp.BaseFragment;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.CitySPUtil;
import com.example.utils.LogUtil;
import com.example.utils.MyLocationListener;
import com.example.utils.ProcessDialogUtil;
import com.example.view.CustomHeader;
import com.kongzue.dialog.v3.WaitDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;

/**
 * 本地商城
 */
public class LocalShopFragment extends BaseFragment<LocalShopView, LocalShopPresenter> implements LocalShopView {
    @BindView(R2.id.local_shop_city)
    TextView localShopCity;
    @BindView(R2.id.local_shop_choose_city)
    LinearLayout localShopChooseCity;
    @BindView(R2.id.local_shop_search)
    TextView localShopSearch;
    @BindView(R2.id.local_shop_order)
    ImageView localShopOrder;
    @BindView(R2.id.local_shop_xbanner)
    XBanner localShopXbanner;
    @BindView(R2.id.local_shop_navbar)
    RecyclerView localShopNavbar;
    @BindView(R2.id.local_shop_pinzhi)
    ImageView localShopPinzhi;
    @BindView(R2.id.local_shop_xinxuan)
    ImageView localShopXinxuan;
    @BindView(R2.id.local_shop_text1)
    TextView localShopText1;
    @BindView(R2.id.local_shop_synthesize_bottom)
    ImageView localShopSynthesizeBottom;
    @BindView(R2.id.local_shop_synthesize)
    RelativeLayout localShopSynthesize;
    @BindView(R2.id.local_shop_text2)
    TextView localShopText2;
    @BindView(R2.id.local_shop_distance_top)
    ImageView localShopDistanceTop;
    @BindView(R2.id.local_shop_distance_bottom)
    ImageView localShopDistanceBottom;
    @BindView(R2.id.local_shop_distance)
    RelativeLayout localShopDistance;
    @BindView(R2.id.local_shop_text3)
    TextView localShopText3;
    @BindView(R2.id.local_shop_score_top)
    ImageView localShopScoreTop;
    @BindView(R2.id.local_shop_score_bottom)
    ImageView localShopScoreBottom;
    @BindView(R2.id.local_shop_score)
    RelativeLayout localShopScore;
    @BindView(R2.id.local_shop_rv_shop)
    RecyclerView localShopRvShop;
    @BindView(R2.id.local_shop_refresh)
    SmartRefreshLayout mRefresh;

    private int page = 1;
    private int index = 0;
    private boolean isFirst = true;
    public static final String ASC = "ASC";     //从小到大
    public static final String DESC = "DESC";   //从大到小
    public static final String STAR = "star";   //评分排序
    public static final String DISTANCE = "distance";   //距离排序

    private static int REQUESTCODE = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_shop;
    }

    @Override
    public void initData() {
        if (!TextUtils.isEmpty(CitySPUtil.getStringValue(CommonResource.CITY))) {
            LogUtil.e("城市" + CitySPUtil.getStringValue(CommonResource.CITY));
            localShopCity.setText(CitySPUtil.getStringValue(CommonResource.CITY));
        } else {
            localShopCity.setText("选择城市");
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        localShopNavbar.setLayoutManager(layoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        localShopRvShop.setLayoutManager(linearLayoutManager);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        mRefresh.setRefreshHeader(customHeader);
    }

    @Override
    public void initClick() {
        localShopPinzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/user/localList").withInt("label", 0).navigation();
            }
        });

        localShopXinxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/user/localList").withInt("label", 1).navigation();
            }
        });

        localShopChooseCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouter.getInstance().build("/module_user_store/LocationActivity").withString("cityName",localShopCity.getText().toString()).navigation();
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                intent.putExtra("cityName", localShopCity.getText().toString());
                startActivityForResult(intent, REQUESTCODE);
            }
        });

        localShopSynthesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                presenter.changeSort(index);
            }
        });

        localShopDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                presenter.changeSort(index);
            }
        });

        localShopScore.setOnClickListener(new View.OnClickListener() {
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
                presenter.loadData(index, page);
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadData(index, page);
            }
        });

        localShopSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToSearch();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && isFirst) {
            ProcessDialogUtil.showProcessDialog(getContext());
//            WaitDialog.show((AppCompatActivity)getActivity(),null);

            if (!TextUtils.isEmpty(CitySPUtil.getStringValue(CommonResource.CITY))) {
                LogUtil.e("城市" + CitySPUtil.getStringValue(CommonResource.CITY));
                localShopCity.setText(CitySPUtil.getStringValue(CommonResource.CITY));
            } else {
                localShopCity.setText("选择城市");
            }
            presenter.initNavbar();
            presenter.initSeller("", "", page, MyLocationListener.longitude, MyLocationListener.latitude);
            presenter.getXBanner();
            presenter.isOpenLocation();

            isFirst = false;
            ModuleBaseApplication.isDingWei = true;
        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventMainThread(EventBusBean eventBusBean) {
//        if (CommonResource.NETCHANGED.equals(eventBusBean.getMsg())) {
//            if (TextUtils.isEmpty(MyLocationListener.district)){
//                localShopCity.setText("定位中");
//            }else{
//                localShopCity.setText(MyLocationListener.district);
//            }
//        }
//    }

//    // Handler异步方式下载图片
//    private Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            if (msg.what == 1) {
//                if (!TextUtils.isEmpty(MyLocationListener.city)) {
//                    localShopCity.setText(MyLocationListener.city);
//                } else {
//                    localShopCity.setText("定位失败");
//                    Toast.makeText(getContext(), "请检查网络设置或者定位信息", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    };

    @Override
    public void loadBanner(List<BannerBean.RecordsBean> beanList) {
        localShopXbanner.setBannerData(beanList);

        localShopXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                RequestOptions requestOptions = RequestOptions.centerCropTransform();
                Glide.with(getContext()).load(((BannerBean.RecordsBean) model).getXBannerUrl()).apply(requestOptions).transform(new RoundedCorners((int) getContext().getResources().getDimension(R.dimen.dp_10))).into((ImageView) view);
            }
        });

        localShopOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToOrder();
            }
        });
    }

    @Override
    public void loadNavbar(LocalNavbarAdapter adapter) {
        localShopNavbar.setAdapter(adapter);
        presenter.initClick();
    }

    @Override
    public void changed(boolean isDistanceJin, boolean isStarMore) {
        localShopText1.setTextColor(Color.parseColor(index == 0 ? "#fd3c15" : "#333333"));
        localShopSynthesizeBottom.setImageResource(index == 0 ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk);
        localShopText2.setTextColor(Color.parseColor(index == 1 ? "#fd3c15" : "#333333"));
        localShopDistanceBottom.setImageResource(index == 1 ? isDistanceJin ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk : R.drawable.khjkjhgjk);
        localShopDistanceTop.setImageResource(index == 1 ? isDistanceJin ? R.drawable.ghfgh : R.drawable.gvhgh : R.drawable.ghfgh);
        localShopText3.setTextColor(Color.parseColor(index == 2 ? "#fd3c15" : "#333333"));
        localShopScoreBottom.setImageResource(index == 2 ? isStarMore ? R.drawable.khjkjhgjk : R.drawable.cgbhdfg : R.drawable.khjkjhgjk);
        localShopScoreTop.setImageResource(index == 2 ? isStarMore ? R.drawable.gvhgh : R.drawable.ghfgh : R.drawable.ghfgh);
    }

    @Override
    public void loadFinish() {
        mRefresh.finishLoadMore();
        mRefresh.finishRefresh();
    }

    @Override
    public void noData() {
        page--;
    }

    @Override
    public void cityName(String cityName) {
        localShopCity.setText(cityName);
    }

    @Override
    public void loadSeller(LocalSellerAdapter adapter) {
        localShopRvShop.setAdapter(adapter);
    }

    @Override
    public LocalShopView createView() {
        return this;
    }

    @Override
    public LocalShopPresenter createPresenter() {
        return new LocalShopPresenter(getContext());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String cityName = data.getStringExtra("cityName");
        if (resultCode == 0) {
            localShopCity.setText(cityName);
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
                            index = 0;
                            changed(false, false);
                            page = 1;
                            presenter.initSeller("", "", page, longitude, latitude);
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
}

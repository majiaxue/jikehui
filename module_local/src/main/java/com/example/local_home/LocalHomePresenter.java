package com.example.local_home;

import android.content.Context;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.BannerBean;
import com.example.bean.LocalNavbarBean;
import com.example.bean.LocalShopBean;
import com.example.bean.LocalShopCommendBean;
import com.example.common.CommonResource;
import com.example.local_home.adapter.LocalHomeCommendAdapter;
import com.example.local_shop.adapter.LocalNavbarAdapter;
import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.module_local.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LocalHomePresenter extends BasePresenter<LocalHomeView> {
    private List<BannerBean.RecordsBean> beanList = new ArrayList<>();
    private LocalNavbarAdapter navbarAdapter;
    private LocalSellerAdapter sellerAdapter;
    private List<LocalNavbarBean> navbarList;
    private List<LocalShopBean> shopBeans = new ArrayList<>();
    private List<String> historyList = new ArrayList<>();

    public LocalHomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(this);
    }

    public void loadData(final int page, double lon, double lat) {
        Map map = MapUtil.getInstance().addParms("page", page).addParms("lon", lon).addParms("lat", lat).build();
        LogUtil.e("这是经度---------"+lon);
        LogUtil.e("这是维度---------"+lat);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.LOCALSHOPLIST, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("本地商城：" + result);
                if (getView() != null) {
                    getView().loadFinish();
                }
                try {
                    if (page == 1) {
                        shopBeans.clear();
                    }
                    shopBeans.addAll(JSON.parseArray(result, LocalShopBean.class));
                    LogUtil.e("本地商城解析后---"+shopBeans.toString());
                    if (sellerAdapter == null) {
                        sellerAdapter = new LocalSellerAdapter(mContext, shopBeans, com.example.user_store.R.layout.rv_local_seller);

                        if (getView() != null) {
                            getView().loadSeller(sellerAdapter);
                        }
                    } else {
                        sellerAdapter.notifyDataSetChanged();
                    }

                    sellerAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
//                            Intent intent = new Intent(mContext, LocalDetailActivity.class);
//                            intent.putExtra("bean", shopBeans.get(position));
//                            mContext.startActivity(intent);
                            ARouter.getInstance().build("/module_local/LocalStoreActivity").withSerializable("bean", shopBeans.get(position)).navigation();
                        }
                    });

                    if (shopBeans.size() == 0 && getView() != null) {
                        getView().noData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-----------" + errorMsg);
                if (getView() != null) {
                    getView().loadFinish();
                }
            }
        }));

    }

    public void getXBanner() {
        //轮播图
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9005).getDataWithout(CommonResource.LOCAL_BANNER);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("本地轮播图：" + result);
                if (result != null) {
                    BannerBean records = JSON.parseObject(result, BannerBean.class);
                    beanList = records.getRecords();
                    if (getView() != null) {
                        getView().loadBanner(beanList);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void initNavbar() {

        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getDataWithout(CommonResource.LOCAL_NAVBAR + "?type=1");
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("本地导航：" + result);
                if (result != null) {
                    navbarList = JSON.parseArray(result, LocalNavbarBean.class);
                    navbarAdapter = new LocalNavbarAdapter(mContext, navbarList, com.example.user_store.R.layout.rv_local_navbar);
                    if (getView() != null) {
                        getView().loadNavbar(navbarAdapter);
                    }

                    navbarAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance().build("/user/localList").withString("type", navbarList.get(position).getSellerCategoryCode()).navigation();
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void initCommend(double lon, double lat, String city) {
        Map map = MapUtil.getInstance().addParms("lon", lon).addParms("lat", lat).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.HOT_SELLERS, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("热门商家：" + result);
                List<LocalShopBean> hotShopBean = JSON.parseArray(result, LocalShopBean.class);
                if (getView() != null) {
                    getView().loadZhongBanner(hotShopBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-----------------" + errorMsg);
            }
        }));

        if (city != null && city.indexOf("市") != -1) {
            city = city.replace("市", "");
        }
        Map map1 = MapUtil.getInstance().addParms("city", city).build();
        Observable<ResponseBody> observable1 = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.LOCAL_SELLERS, map1);
        RetrofitUtil.getInstance().toSubscribe(observable1, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("本地商家推荐：" + result);
                List<LocalShopCommendBean> localShopCommendBean = JSON.parseArray(result, LocalShopCommendBean.class);
                List<LocalShopCommendBean.GoodsListBean> goodsList = localShopCommendBean.get(0).getGoodsList();
                LocalHomeCommendAdapter commendAdapter = new LocalHomeCommendAdapter(mContext, goodsList, R.layout.rv_local_home_commend);
                if (getView() != null) {
                    getView().loadCommend(localShopCommendBean.get(0), commendAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "----------------" + errorMsg);
            }
        }));
    }

    public void isOpenLocation() {
        LocationManager locManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // 未打开位置开关，可能导致定位失败或定位不准，提示用户或做相应处理
//            PopUtils.location(mContext);
            // 新建一个子线程来发送消息
            new Thread() {
                @Override
                public void run() {
                    try {
                        // 让ProgressDialog显示一会儿。。。。
                        Thread.sleep(1000);
                        Message message = new Message();
                        message.what = 1;
                        // 发送消息到消息队列中
                        handler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    // Handler异步方式
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
//                PopUtils.location(mContext);
                Toast.makeText(mContext, "未开启GPS定位", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void jumpToSearch() {
        ARouter.getInstance().build("/module_user_store/UserSearchActivity").withString("from", CommonResource.HISTORY_LOCAL).navigation();
    }
}

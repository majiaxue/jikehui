package com.example.local_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.LocalShopBean;
import com.example.common.CommonResource;
import com.example.local_shop.LocalShopFragment;
import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.MyLocationListener;
import com.example.utils.ProcessDialogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class LocalListPresenter extends BasePresenter<LocalListView> {
    private List<LocalShopBean> shopBeans = new ArrayList<>();
    private LocalSellerAdapter sellerAdapter;
    private String type;
    private String search;
    private int label;

    private boolean isZh = true;

    private boolean isDistance = false;
    private boolean isDistanceJin = true;
    private boolean distanceFlag = false;   //默认false,点击评分后先判断是否为true，如果为true则改变评分正倒序，否则不改变，然后改为true，点击其他排序改为false

    private boolean isStar = false;
    private boolean isStarMore = true;
    private boolean starFlag = false;


    public LocalListPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(String type, String search, String sorttype, String sort, final int page, int label) {
        this.type = type;
        this.search = search;
        this.label = label;
        Map map = null;
        if (label != 1 && label != 2) {
            map = MapUtil.getInstance().addParms("sort", sort + " " + sorttype).addParms("page", page).addParms("lon", MyLocationListener.longitude).addParms("lat", MyLocationListener.latitude).addParms("sellerCategory", type).addParms("sellerShopName", search).build();
        } else {
            map = MapUtil.getInstance().addParms("sort", sort + " " + sorttype).addParms("page", page).addParms("lon", MyLocationListener.longitude).addParms("lat", MyLocationListener.latitude).addParms("sellerCategory", type).addParms("sellerShopName", search).addParms("label", label).build();
        }

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
                    if (shopBeans.size() > 0) {
                        if (sellerAdapter == null) {
                            sellerAdapter = new LocalSellerAdapter(mContext, shopBeans, R.layout.rv_local_seller);
                            if (getView() != null) {
                                getView().loadSeller(sellerAdapter);
                            }
                        } else {
                            sellerAdapter.notifyDataSetChanged();
                        }

                        sellerAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
//                                Intent intent = new Intent(mContext, LocalDetailActivity.class);
//                                intent.putExtra("bean", shopBeans.get(position));
//                                mContext.startActivity(intent);
                                ARouter.getInstance().build("/module_local/LocalStoreActivity").withSerializable("bean", shopBeans.get(position)).navigation();
                            }
                        });
                    } else {
                        if (getView() != null) {
                            getView().noData();
                        }
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

    public void changeSort(int index) {
        if (index == 0) {
            isDistance = false;
            isStar = false;
            distanceFlag = false;
            starFlag = false;
            if (!isZh) {
                isZh = true;
                ProcessDialogUtil.showProcessDialog(mContext);
//                WaitDialog.show((AppCompatActivity)mContext,null);

                loadData(type, search, "", "", 1, label);
            }
        } else if (index == 1) {
            isZh = false;
            isStar = false;
            isDistance = true;
            starFlag = false;
            if (distanceFlag) {
                isDistanceJin = !isDistanceJin;
            }
            distanceFlag = true;
            ProcessDialogUtil.showProcessDialog(mContext);
//            WaitDialog.show((AppCompatActivity)mContext,null);

            loadData(type, search, isDistanceJin ? LocalShopFragment.ASC : LocalShopFragment.DESC, LocalShopFragment.DISTANCE, 1, label);
        } else if (index == 2) {
            isZh = false;
            isDistance = false;
            isStar = true;
            distanceFlag = false;
            if (starFlag) {
                isStarMore = !isStarMore;
            }
            starFlag = true;
            ProcessDialogUtil.showProcessDialog(mContext);
//            WaitDialog.show((AppCompatActivity)mContext,null);

            loadData(type, search, isStarMore ? LocalShopFragment.DESC : LocalShopFragment.ASC, LocalShopFragment.STAR, 1, label);
        }

        getView().changed(isDistanceJin, isStarMore);
    }

    public void refresh(int index, int page) {
        if (index == 0) {
            loadData(type, search, "", "", page, label);
        } else if (index == 1) {
            loadData(type, search, isDistanceJin ? LocalShopFragment.ASC : LocalShopFragment.DESC, LocalShopFragment.DISTANCE, page, label);
        } else if (index == 2) {
            loadData(type, search, isStarMore ? LocalShopFragment.DESC : LocalShopFragment.ASC, LocalShopFragment.STAR, page, label);
        }
    }

    public void jumpToSearch() {
        ARouter.getInstance().build("/module_user_store/UserSearchActivity").withString("from", CommonResource.HISTORY_LOCAL).navigation();
//        Intent intent = new Intent(mContext, UserSearchActivity.class);
//        intent.putExtra("from", CommonResource.HISTORY_LOCAL);
//        mContext.startActivity(intent);
    }
}

package com.example.shop_home.treasure;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.HotSaleBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.type_detail.adapter.TypeDetailLstAdapter;
import com.example.type_detail.adapter.TypeDetailWaterfallAdapter;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class ShopTreasurePresenter extends BasePresenter<ShopTreasureView> {

    private List<HotSaleBean.DataBean> dataList = new ArrayList<>();
    private TypeDetailLstAdapter lstAdapter;
    private TypeDetailWaterfallAdapter waterfallAdapter;

    private boolean isWaterfall = false;
    private boolean isPositiveSalesVolume = false;  //是否销量排行
    private boolean isSalesVolumeReduce = false;     //是否销量从高到低
    private boolean saleVolumTemp = false;

    private boolean isPositivePrice = false;        //是否价格排行
    private boolean isPriceReduce = true;          //是否价格从高到低
    private boolean priceTemp = false;

    private boolean isPositiveCredit = false;       //是否信用排行
    private boolean isCreditReduce = false;          //是否信用从高到低
    private boolean creditTemp = false;
    private int flag = 0;

    public ShopTreasurePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(String sellerId,String categoryId, final int page) {
        Map map = MapUtil.getInstance().addParms("sellerId", sellerId).addParms("categoryId",categoryId).addParms("pageNum", page).addParms("pageSize", "2000").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.HOTNEWSEARCH, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                if (getView() != null) {
                    getView().loadFinish();
                }
                LogUtil.e("店铺商品：" + result);
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                dataList.addAll(hotSaleBean.getData());
                lstAdapter = new TypeDetailLstAdapter(mContext, dataList, R.layout.rv_type_detail_lst);
                waterfallAdapter = new TypeDetailWaterfallAdapter(mContext, dataList, R.layout.rv_commend);
                getView().loadLstRv(lstAdapter, flag);

                lstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_user_store/GoodsDetailActivity")
                                .withString("id", dataList.get(position).getId() + "")
                                .withString("sellerId", dataList.get(position).getSellerId())
                                .withString("commendId", dataList.get(position).getProductCategoryId() + "")
                                .navigation();
//                        mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
                    }
                });

                waterfallAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_user_store/GoodsDetailActivity")
                                .withString("id", dataList.get(position).getId() + "")
                                .withString("sellerId", dataList.get(position).getSellerId())
                                .withString("commendId", dataList.get(position).getProductCategoryId() + "")
                                .navigation();
//                        mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
                    }
                });
                flag = dataList.size();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "------------" + errorMsg);
            }
        }));
    }


    public void ChangeShow() {
        if (isWaterfall) {
            getView().loadLstRv(lstAdapter, flag);
            isWaterfall = false;
        } else {
            getView().loadWaterfallRv(waterfallAdapter, flag);
            isWaterfall = true;
        }
    }

    public void changeTyep(int index, String sellerId) {
        isPositiveSalesVolume = index == 1 ? !isPositiveSalesVolume : false;
        isSalesVolumeReduce = index == 1 ? !isSalesVolumeReduce : false;
        saleVolumTemp = index == 1 ? true : false;

        isPositivePrice = index == 2 ? !isPositivePrice : false;
        isPriceReduce = index == 2 ? !isPriceReduce : true;
        priceTemp = index == 2 ? true : false;

        isPositiveCredit = index == 3 ? !isPositiveCredit : false;
        isCreditReduce = index == 3 ? !isCreditReduce : false;
        creditTemp = index == 3 ? true : false;
        loadMore(sellerId, 1);
        getView().updateTitle(isPositiveSalesVolume, isPositivePrice, isPositiveCredit);

    }

    public void loadMore(String sellerId, final int page) {
        Map map;
        if (saleVolumTemp) {
            if (isSalesVolumeReduce) {
                map = MapUtil.getInstance().addParms("sellerId", sellerId).addParms("saleDesc", "1").addParms("pageNum", page).addParms("pageSize", "2000").build();
            } else {
                map = MapUtil.getInstance().addParms("sellerId", sellerId).addParms("saleAsc", "1").addParms("pageNum", page).addParms("pageSize", "2000").build();
            }
        } else if (priceTemp) {
            if (isPriceReduce) {
                map = MapUtil.getInstance().addParms("sellerId", sellerId).addParms("pageNum", page).addParms("priceDesc", "1").addParms("pageSize", "2000").build();
            } else {
                map = MapUtil.getInstance().addParms("sellerId", sellerId).addParms("pageNum", page).addParms("priceAsc", "1").addParms("pageSize", "2000").build();
            }
        } else if (creditTemp) {
            if (isCreditReduce) {
                map = MapUtil.getInstance().addParms("sellerId", sellerId).addParms("pageNum", page).addParms("pageSize", "2000").build();
            } else {
                map = MapUtil.getInstance().addParms("sellerId", sellerId).addParms("pageNum", page).addParms("pageSize", "2000").build();
            }
        } else {
            map = MapUtil.getInstance().addParms("sellerId", sellerId).addParms("pageNum", page).addParms("pageSize", "2000").build();
        }

        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.HOTNEWSEARCH, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                if (getView() != null) {
                    getView().loadFinish();
                }
                LogUtil.e("店铺详情：" + result);
                if (page == 1) {
                    dataList.clear();
                }
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                dataList.addAll(hotSaleBean.getData());

                if (isWaterfall) {
//                    getView().loadWaterfallRv(waterfallAdapter, 0);
                    waterfallAdapter.notifyDataSetChanged();
                } else {
//                    getView().loadLstRv(lstAdapter, 0);
                    lstAdapter.notifyDataSetChanged();
                }

                flag = dataList.size();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "------------" + errorMsg);
            }
        }));
    }
}

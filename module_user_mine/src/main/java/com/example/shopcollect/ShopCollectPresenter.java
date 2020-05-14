package com.example.shopcollect;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.HotSaleBean;
import com.example.common.CommonResource;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.shopcollect.adapter.ShopCollectAdapter;
import com.example.bean.ShopCollectBean;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.example.view.SelfDialog;
import com.example.view.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class ShopCollectPresenter extends BasePresenter<ShopCollectView> {

    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;
    private List<BaseRecImageAndTextBean> list;
    private ShopCollectAdapter shopCollectAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<HotSaleBean.DataBean> commendList = new ArrayList<>();
    private List<ShopCollectBean.RecordsBean> dataBeanList = new ArrayList<>();

    public ShopCollectPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initShopCollectRec(final SlideRecyclerView shopCollectRec) {
        final Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.SELLERPAGE, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(final String result, String msg) {
                LogUtil.e("result--------->" + result);
                if (result != null) {
                    ShopCollectBean shopCollectBean = JSON.parseObject(result, new TypeReference<ShopCollectBean>() {
                    }.getType());
                    if (shopCollectBean != null) {
                        dataBeanList.clear();
                        dataBeanList.addAll(shopCollectBean.getRecords());
                        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                        shopCollectRec.setLayoutManager(linearLayoutManager);
                        if (shopCollectAdapter == null) {
                            shopCollectAdapter = new ShopCollectAdapter(mContext, dataBeanList, R.layout.item_shop_collect_rec);
                            shopCollectRec.setAdapter(shopCollectAdapter);
                        } else {
                            shopCollectAdapter.notifyDataSetChanged();
                        }

                        shopCollectAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
//                            Toast.makeText(mContext, "进入店铺", Toast.LENGTH_SHORT).show();
                                ARouter.getInstance().build("/module_user_store/ShopHomeActivity")
                                        .withString("sellerId", dataBeanList.get(position).getId() + "").navigation();
                            }
                        });
                        shopCollectAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
                            @Override
                            public void ViewTwoOnClick(View view1, View view2, final int position) {
                                view1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                view2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //取消店铺
                                        final SelfDialog selfDialog = new SelfDialog(mContext);
                                        selfDialog.setTitle("提示");
                                        selfDialog.setMessage("您确定要取消关注此店铺吗？");
                                        selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                                            @Override
                                            public void onYesClick() {
                                                int favoriteId = dataBeanList.get(position).getId();
                                                LogUtil.e("shopCollect--------->" + favoriteId);
                                                Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).deleteDataWithout(CommonResource.SHOPDELETE + "/" + favoriteId, SPUtil.getToken());
                                                RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                                                    @Override
                                                    public void onSuccess(String result, String msg) {
                                                        LogUtil.e("shopCollect--------->" + msg);
                                                        dataBeanList.remove(position);
                                                        shopCollectAdapter.notifyDataSetChanged();
                                                        shopCollectRec.closeMenu();
                                                        selfDialog.dismiss();
                                                        PopUtils.setTransparency(mContext, 1f);
                                                    }

                                                    @Override
                                                    public void onError(String errorCode, String errorMsg) {
                                                        LogUtil.e("shopCollect--------->" + errorMsg);
                                                    }
                                                }));


                                            }
                                        });
                                        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                                            @Override
                                            public void onNoClick() {
                                                selfDialog.dismiss();
                                                shopCollectRec.closeMenu();
                                                PopUtils.setTransparency(mContext, 1f);
                                            }
                                        });
                                        PopUtils.setTransparency(mContext, 0.3f);
                                        selfDialog.show();
                                    }
                                });
                            }
                        });
                    } else {
                        dataBeanList.clear();
                        shopCollectAdapter = new ShopCollectAdapter(mContext, dataBeanList, R.layout.item_shop_collect_rec);
                        shopCollectRec.setAdapter(shopCollectAdapter);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("errorMSG----->" + errorMsg);
            }
        }));


    }


    public void shopCollectBottomRec(final RecyclerView shopCollectBottomRec) {
//        Map map = MapUtil.getInstance().addParms("searchInfo", "俩件套").build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.HOTNEWSEARCH);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                commendList.clear();
                commendList.addAll(hotSaleBean.getData());
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                //添加间距
                spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
                if (shopCollectBottomRec.getItemDecorationCount() == 0) {
                    shopCollectBottomRec.addItemDecoration(spaceItemDecorationLeftAndRight);
                }
                shopCollectBottomRec.setLayoutManager(staggeredGridLayoutManager);
                BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, commendList, R.layout.item_base_rec_staggered_grid);
                shopCollectBottomRec.setAdapter(baseRecStaggeredAdapter);

                baseRecStaggeredAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance()
                                .build("/module_user_store/GoodsDetailActivity")
                                .withString("id", commendList.get(position).getId() + "")
                                .withString("sellerId", commendList.get(position).getSellerId())
                                .withString("commendId", commendList.get(position).getProductCategoryId() + "")
                                .navigation();
                    }
                });
                baseRecStaggeredAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                    @Override
                    public void ViewOnClick(View view, final int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "position:" + index, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("errorMsg------->" + errorMsg);
            }
        }));

    }
}

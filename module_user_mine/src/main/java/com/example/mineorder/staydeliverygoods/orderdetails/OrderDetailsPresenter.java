package com.example.mineorder.staydeliverygoods.orderdetails;

import android.content.Context;
import android.os.CountDownTimer;
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
import com.example.bean.OrderDetailBean;
import com.example.common.CommonResource;
import com.example.mineorder.staydeliverygoods.orderdetails.adapter.OrderDetailsAdapter;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/6/16
 * Describe:
 */
public class OrderDetailsPresenter extends BasePresenter<OrderDetailsView> {

    private List<HotSaleBean.DataBean> commendList = new ArrayList<>();

    public OrderDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(String orderSn) {
        ProcessDialogUtil.showProcessDialog(mContext);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).getHeadWithout(CommonResource.ORDER_DETAIL + "/" + orderSn, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("订单详情：" + result);
                OrderDetailBean orderDetailBean = JSON.parseObject(result, new TypeReference<OrderDetailBean>() {
                }.getType());
                LogUtil.e("订单orderDetailBean" + orderDetailBean);
                if (getView() != null) {
                    getView().loadData(orderDetailBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void items(List<OrderDetailBean.ItemsBean> items, RecyclerView orderDetailsGoodsRec) {
        LogUtil.e("item" + items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(mContext, items, R.layout.item_order_details_rec);
        orderDetailsGoodsRec.setLayoutManager(linearLayoutManager);
        orderDetailsGoodsRec.setAdapter(orderDetailsAdapter);

    }


    public void orderDetailsRecommendRec(final RecyclerView orderDetailsRecommendRec) {

//        Map map = MapUtil.getInstance().addParms("searchInfo", "两件套").build();
        ProcessDialogUtil.showProcessDialog(mContext);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.HOTNEWSEARCH);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {

                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                if (hotSaleBean != null) {

                    commendList.clear();
                    commendList.addAll(hotSaleBean.getData());
                    StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    //添加间距
                    SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
                    if (orderDetailsRecommendRec.getItemDecorationCount() == 0) {
                        orderDetailsRecommendRec.addItemDecoration(spaceItemDecorationLeftAndRight);
                    }
                    orderDetailsRecommendRec.setLayoutManager(staggeredGridLayoutManager);
                    BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, commendList, R.layout.item_base_rec_staggered_grid);
                    orderDetailsRecommendRec.setAdapter(baseRecStaggeredAdapter);

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
//                                    Toast.makeText(mContext, "position:" + index, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("errorMsg------->" + errorMsg);
            }
        }));

    }


}

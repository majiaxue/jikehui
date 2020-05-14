package com.example.logisticsinformation;

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
import com.example.logisticsinformation.adapter.LogisticsInforMationAdapter;
import com.example.bean.LogisticsInforMationBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/28
 * Describe:
 */
public class LogisticsInformationPresenter extends BasePresenter<LogisticsInformationView> {

    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;
    private List<HotSaleBean.DataBean> commendList = new ArrayList<>();
    private List<LogisticsInforMationBean.TracesBean> inforMationBeanList = new ArrayList<>();


    public LogisticsInformationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    //物流信息
    public void logisticsInformationMessageRec(final RecyclerView logisticsInformationMessageRec, String orderSn) {

        Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postDataWithout(CommonResource.GETORDERTRACESBYJSON + "/" + orderSn);
        RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("物流信息----->" + result);
                if (!"".equals(result)) {
                    LogisticsInforMationBean logisticsInforMationBean = new Gson().fromJson(result, LogisticsInforMationBean.class);
                    if (logisticsInforMationBean != null) {
                        inforMationBeanList.clear();
                        inforMationBeanList.addAll(logisticsInforMationBean.getTraces());
                        Collections.reverse(inforMationBeanList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                        logisticsInformationMessageRec.setLayoutManager(linearLayoutManager);
                        LogisticsInforMationAdapter logisticsInforMationAdapter = new LogisticsInforMationAdapter(mContext, inforMationBeanList, R.layout.item_logistics_information_rec);
                        logisticsInformationMessageRec.setAdapter(logisticsInforMationAdapter);

                        if (getView()!=null){
                            getView().traces(logisticsInforMationBean,inforMationBeanList.size());
                        }

                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));



    }

    //推荐
    public void logisticsInformationRec(final RecyclerView logisticsInformationRec) {
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
                if (logisticsInformationRec.getItemDecorationCount() == 0) {
                    logisticsInformationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
                }
                logisticsInformationRec.setLayoutManager(staggeredGridLayoutManager);
                BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, commendList, R.layout.item_base_rec_staggered_grid);
                logisticsInformationRec.setAdapter(baseRecStaggeredAdapter);

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

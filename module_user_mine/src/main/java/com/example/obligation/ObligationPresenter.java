package com.example.obligation;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import com.example.utils.MapUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.example.utils.TxtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/28
 * Describe:
 */
public class ObligationPresenter extends BasePresenter<ObligationView> {

    private List<HotSaleBean.DataBean> commendList = new ArrayList<>();
    private OrderDetailBean orderDetailBean;

    public ObligationPresenter(Context context) {
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
                orderDetailBean = JSON.parseObject(result, OrderDetailBean.class);
                if (getView() != null) {
                    getView().loadData(orderDetailBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("订单详情失败：" + errorMsg);
            }
        }));
    }

    public void items(List<OrderDetailBean.ItemsBean> items, RecyclerView orderDetailsGoodsRec) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(mContext, items, R.layout.item_order_details_rec);
        orderDetailsGoodsRec.setLayoutManager(linearLayoutManager);
        orderDetailsGoodsRec.setAdapter(orderDetailsAdapter);

    }

    public void obligationRec(final RecyclerView obligationRec) {
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
                SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
                if (obligationRec.getItemDecorationCount() == 0) {
                    obligationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
                }
                obligationRec.setLayoutManager(staggeredGridLayoutManager);
                BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, commendList, R.layout.item_base_rec_staggered_grid);
                obligationRec.setAdapter(baseRecStaggeredAdapter);

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
//                                Toast.makeText(mContext, "position:" + index, Toast.LENGTH_SHORT).show();
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

    public void popupCancellationOrder() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_cancellation_order, null);
        TextView text = view.findViewById(R.id.popup_cancellation_order_text);
        TxtUtil.txtJianbian(text, "#feb60e", "#fb4419");
        final ImageView imageClose = view.findViewById(R.id.popup_cancellation_order_close);
        final RadioGroup popupRefundRadio = view.findViewById(R.id.popup_cancellation_order_radio);
        final RadioButton but1 = view.findViewById(R.id.popup_cancellation_order_but1);
        final RadioButton but2 = view.findViewById(R.id.popup_cancellation_order_but2);
        final RadioButton but3 = view.findViewById(R.id.popup_cancellation_order_but3);
        final RadioButton but4 = view.findViewById(R.id.popup_cancellation_order_but4);
        final RadioButton but5 = view.findViewById(R.id.popup_cancellation_order_but5);
        final TextView submit = view.findViewById(R.id.popup_cancellation_order_submit);
        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(mContext, 375), new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                imageClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });

//                submit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (but1.isChecked()){
//                            Toast.makeText(mContext, but1.getText().toString(), Toast.LENGTH_SHORT).show();
//                            pop.dismiss();
//                        }else if (but2.isChecked()){
//                            Toast.makeText(mContext, but2.getText().toString(), Toast.LENGTH_SHORT).show();
//                            pop.dismiss();
//                        }else if (but3.isChecked()){
//                            Toast.makeText(mContext, but3.getText().toString(), Toast.LENGTH_SHORT).show();
//                            pop.dismiss();
//                        }else if (but4.isChecked()){
//                            Toast.makeText(mContext, but4.getText().toString(), Toast.LENGTH_SHORT).show();
//                            pop.dismiss();
//                        }else if (but5.isChecked()){
//                            Toast.makeText(mContext, but5.getText().toString(), Toast.LENGTH_SHORT).show();
//                            pop.dismiss();
//                        }
//                    }
//                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map build = MapUtil.getInstance().addParms("orderId", orderDetailBean.getItems().get(0).getOrderId()).build();
                        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERREMOVE, build, SPUtil.getToken());
                        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
                            @Override
                            public void onSuccess(String result, String msg) {
                                LogUtil.e("删除---------->" + result);
                                if ("true".equals(result)) {
                                    if (getView() != null) {
                                        getView().isDelete(true);
                                    }
                                }
                            }

                            @Override
                            public void onError(String errorCode, String errorMsg) {
                                LogUtil.e("删除---------->" + errorMsg);
                            }
                        }));
                    }
                });


            }
        });
        PopUtils.setTransparency(mContext, 0.3f);
    }
}

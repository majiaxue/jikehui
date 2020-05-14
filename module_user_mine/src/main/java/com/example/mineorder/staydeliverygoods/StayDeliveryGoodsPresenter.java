package com.example.mineorder.staydeliverygoods;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.common.CommonResource;
import com.example.mineorder.adapter.MineOrderParentAdapter;
import com.example.bean.MineOrderBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.utils.CustomDialog;
import com.google.gson.Gson;
import com.kongzue.dialog.v3.WaitDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:待收货
 */
public class StayDeliveryGoodsPresenter extends BasePresenter<StayDeliveryGoodsView> {

    private List<MineOrderBean.OrderListBean> listBeans = new ArrayList<>();
    private MineOrderParentAdapter mineOrderParentAdapter;

    public StayDeliveryGoodsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void stayDeliveryGoodsRec() {
        ProcessDialogUtil.showProcessDialog(mContext);
//        WaitDialog.show((AppCompatActivity)mContext,null);

        Map map = MapUtil.getInstance().addParms("status", 2).build();
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERSTATUS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(final String result, String msg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("stayDeliveryGoodsResult" + result);
//                MineOrderBean mineOrderBean = JSON.parseObject(result, new TypeReference<MineOrderBean>() {
//                }.getType());
//                LogUtil.e("MineOrderBean" + mineOrderBean.getOrderList());
                MineOrderBean mineOrderBean = new Gson().fromJson(result, MineOrderBean.class);
                if (mineOrderBean != null) {
                    listBeans.clear();
                    listBeans.addAll(mineOrderBean.getOrderList());
                    if (mineOrderParentAdapter == null) {
                        mineOrderParentAdapter = new MineOrderParentAdapter(mContext, listBeans, R.layout.item_mine_order_parent_rec);
                    } else {
                        mineOrderParentAdapter.notifyDataSetChanged();
                    }

                    if (getView() != null) {
                        getView().load(mineOrderParentAdapter);
                    }
                    mineOrderParentAdapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
                        @Override
                        public void ViewThreeOnClick(View view1, View view2, View view3, final int position) {

                            //取消申请
                            view2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (listBeans.get(position).getBackStatus() == 0 || listBeans.get(position).getBackStatus() == 1) {
                                        //取消申请
                                        Map map = MapUtil.getInstance().addParms("orderSn", listBeans.get(position).getOrderSn()).build();
                                        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postData(CommonResource.CANCEL_TUIKUAN, map);
                                        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                                            @Override
                                            public void onSuccess(String result, String msg) {
                                                LogUtil.e("取消申请退款：" + result);
                                                stayDeliveryGoodsRec();
                                            }

                                            @Override
                                            public void onError(String errorCode, String errorMsg) {
                                                LogUtil.e(errorCode + "---------------" + errorMsg);
                                            }
                                        }));
                                    } else if (listBeans.get(position).getBackStatus() == 2) {
                                        Map build = MapUtil.getInstance().addParms("orderId", listBeans.get(position).getOrderId()).build();
                                        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERREMOVE, build, SPUtil.getToken());
                                        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
                                            @Override
                                            public void onSuccess(String result, String msg) {
                                                if ("true".equals(result)) {
                                                    listBeans.remove(position);
                                                    mineOrderParentAdapter.notifyDataSetChanged();
                                                }
                                            }

                                            @Override
                                            public void onError(String errorCode, String errorMsg) {
                                                LogUtil.e("删除errorMsg---------->" + errorMsg);
                                            }
                                        }));
                                    } else {
                                        //查看物流
                                        ARouter.getInstance()
                                                .build("/module_user_mine/LogisticsInformationActivity")
                                                .withString("orderSn", listBeans.get(position).getOrderItems().get(0).getOrderSn())
                                                .withString("goodsImage", listBeans.get(position).getOrderItems().get(0).getProductPic())
                                                .navigation();
                                    }
                                }
                            });
                            //确认收货
                            view3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithout(CommonResource.ORDERCONFIRM + "/" + listBeans.get(position).getOrderId(), SPUtil.getToken());
                                    RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
                                        @Override
                                        public void onSuccess(String result, String msg) {
                                            LogUtil.e("确认收货---->" + result);
                                            listBeans.remove(position);
                                            mineOrderParentAdapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onError(String errorCode, String errorMsg) {
                                            LogUtil.e("确认收货error---->" + errorMsg);
                                        }
                                    }));

                                }
                            });
                        }
                    });

                    mineOrderParentAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance()
                                    .build("/module_user_mine/OrderDetailsActivity")
                                    .withString("orderSn", listBeans.get(0).getOrderItems().get(position).getOrderSn())
                                    .navigation();
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("stayDeliveryGoodsErrorMsg" + errorMsg);
            }
        }));

    }

}

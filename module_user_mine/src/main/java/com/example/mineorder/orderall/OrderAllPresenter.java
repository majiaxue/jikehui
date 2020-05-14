package com.example.mineorder.orderall;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.MineOrderBean;
import com.example.bean.SubmitOrderBean;
import com.example.common.CommonResource;
import com.example.mineorder.adapter.MineOrderParentAdapter;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:全部订单
 */
public class OrderAllPresenter extends BasePresenter<OrderAllView> {
    private MineOrderParentAdapter mineOrderParentAdapter;

    private List<MineOrderBean.OrderListBean> listBeans = new ArrayList<>();

    public OrderAllPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void orderAllRec() {
        ProcessDialogUtil.showProcessDialog(mContext);

        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.ORDERALL, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("OrderAllPresenterResult-------->" + result);

                final MineOrderBean mineOrderBean = new Gson().fromJson(result, MineOrderBean.class);
                LogUtil.e("MineOrderBean1" + mineOrderBean);
                if (mineOrderBean != null) {

                    if (mineOrderBean.getOrderList() != null && mineOrderBean.getOrderList().size() != 0) {
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

                        mineOrderParentAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                if (listBeans.get(position).getStatus() == 1) {
                                    ARouter.getInstance()
                                            .build("/module_user_mine/OrderDetailsActivity")
                                            .withString("orderSn", listBeans.get(position).getOrderItems().get(0).getOrderSn())
                                            .navigation();
                                } else if (listBeans.get(position).getStatus() == 2) {
                                    ARouter.getInstance()
                                            .build("/module_user_mine/OrderDetailsActivity")
                                            .withString("orderSn", listBeans.get(position).getOrderItems().get(0).getOrderSn())
                                            .navigation();
                                } else if (listBeans.get(position).getStatus() == 6) {
                                    //待付款
                                    ARouter.getInstance()
                                            .build("/module_user_mine/ObligationActivity")
                                            .withString("orderSn", listBeans.get(position).getOrderItems().get(0).getOrderSn())
                                            .navigation();
                                }
                            }
                        });

                        mineOrderParentAdapter.setViewThreeOnClickListener(new MyRecyclerAdapter.ViewThreeOnClickListener() {
                            @Override
                            public void ViewThreeOnClick(View view1, View view2, View view3, final int position) {
                                final TextView left = view2.findViewById(R.id.mine_order_parent_btn_left);

                                //去店铺
                                view1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                });
                                int status = listBeans.get(position).getStatus();
                                if (status == 1) {
                                    //1待发货
                                    //取消申请
                                    view2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (listBeans.get(position).getBackStatus() == 0) {
                                                cancelShenqing(listBeans.get(position).getOrderSn());
                                            } else if (listBeans.get(position).getBackStatus() == 2) {
                                                deleteOrder(position);
                                            } else if (listBeans.get(position).getBackStatus() == -1) {
                                                if ("申请退款".equals(left.getText().toString())) {
                                                    ARouter.getInstance()
                                                            .build("/module_user_mine/RefundActivity")
                                                            .withSerializable("mineOrderBean", mineOrderBean)
                                                            .withInt("position", position)
                                                            .withString("type", "0")
                                                            .navigation();
                                                }
                                            }
                                        }
                                    });
                                    //提醒发货
                                    view3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Map orderSn = MapUtil.getInstance().addParms("orderSn", listBeans.get(position).getOrderSn()).build();
                                            Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).getData(CommonResource.DELIVERGOODSREMIND, orderSn);
                                            RetrofitUtil.getInstance().toSubscribe(data,new OnMyCallBack(new OnDataListener() {
                                                @Override
                                                public void onSuccess(String result, String msg) {
                                                    LogUtil.e("提醒发货"+result);
                                                    Toast.makeText(mContext, "已提醒商家发货!", Toast.LENGTH_SHORT).show();
                                                }

                                                @Override
                                                public void onError(String errorCode, String errorMsg) {
                                                    LogUtil.e("提醒发货"+errorMsg);
                                                }
                                            }));
                                        }
                                    });
                                } else if (status == 6) {
                                    //6待付款
                                    //取消订单
                                    view2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            cancelOrder(position);
                                        }
                                    });
                                    //付款
                                    view3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            SubmitOrderBean submitOrderBean = new SubmitOrderBean();
                                            submitOrderBean.setTotalAmount(listBeans.get(position).getPayAmount());
                                            submitOrderBean.setMasterNo(listBeans.get(position).getOrderItems().get(0).getOrderSn());
                                            ARouter.getInstance().build("/module_user_store/PaymentActivity")
                                                    .withSerializable("submitOrderBean", submitOrderBean)
                                                    .navigation();
                                        }
                                    });
                                } else if (status == 2) {
                                    //2待收货
                                    //取消申请
                                    view2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (listBeans.get(position).getBackStatus() == 0 || listBeans.get(position).getBackStatus() == 1) {
                                                cancelShenqing(listBeans.get(position).getOrderSn());
                                            } else if (listBeans.get(position).getBackStatus() == 2) {
                                                deleteOrder(position);
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
                                                        orderAllRec();
                                                }

                                                @Override
                                                public void onError(String errorCode, String errorMsg) {
                                                    LogUtil.e("确认收货error---->" + errorMsg);
                                                }
                                            }));

                                        }
                                    });
                                } else if (status == 4 || status == 5) {
                                    //45 已失效
                                    //删除订单
                                    view2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            deleteOrder(position);
                                        }
                                    });
                                    //再次购买
                                    view3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ARouter.getInstance()
                                                    .build("/module_user_store/GoodsDetailActivity")
                                                    .withString("id", listBeans.get(position).getOrderItems().get(0).getProductId() + "")
                                                    .withString("sellerId", listBeans.get(position).getSellerId())
                                                    .withString("commendId", listBeans.get(position).getOrderItems().get(0).getProductCategoryId() + "")
                                                    .navigation();
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("OrderAllPresenterError-------->" + errorMsg);
            }
        }));

    }

    /**
     * 删除订单
     *
     * @param position
     */
    private void deleteOrder(final int position) {
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
    }

    /**
     * 取消订单
     */
    private void cancelOrder(final int position) {
        String jsonString = JSON.toJSONString(listBeans.get(position));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);

        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postDataWithBody(CommonResource.CANCEL_ORDER, requestBody);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                if ("true".equals(result)) {
                    orderAllRec();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("删除errorMsg---------->" + errorMsg);
            }
        }));
    }

    /**
     * 取消申请退款
     */
    private void cancelShenqing(String orderSn) {
        Map map = MapUtil.getInstance().addParms("orderSn", orderSn).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postData(CommonResource.CANCEL_TUIKUAN, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("取消申请退款：" + result);
                orderAllRec();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "---------------" + errorMsg);
            }
        }));
    }
}

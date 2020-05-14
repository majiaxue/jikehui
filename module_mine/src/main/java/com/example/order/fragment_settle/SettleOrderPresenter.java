package com.example.order.fragment_settle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.JDOrderBean;
import com.example.bean.MyOrderBean;
import com.example.bean.TBGoodsDetailsBean;
import com.example.bean.TBOrderBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.order.OrderActivity;
import com.example.order.adapter.JDAdapter;
import com.example.order.adapter.RvListAdapter;
import com.example.order.adapter.TBAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class SettleOrderPresenter extends BasePresenter<SettleOrderView> {
    private List<MyOrderBean> dataList;
    private RvListAdapter adapter;
    private TBAdapter tbAdapter;
    private List<TBOrderBean> orderBeans;
    private int flag = 0;
    private long currentTime = 0;//判断点击时间间隔

    public SettleOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        ProcessDialogUtil.showProcessDialog(mContext);
//        WaitDialog.show((AppCompatActivity)mContext,null);

        if (OrderActivity.index == 0) {
            scOrder();
        } else if (OrderActivity.index == 1) {
            tbOrder();
        } else if (OrderActivity.index == 2) {
            jdOrder();
        } else if (OrderActivity.index == 3) {
            pddOrder();
        }

    }

    private void scOrder() {

    }

    private void tbOrder() {
        Map map = MapUtil.getInstance().addParms("status", 1).addParms("type", 0).addParms("current", "1").addParms("pageSize", "1000").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_PDD_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("淘宝已结算：" + result);
                orderBeans = JSON.parseArray(result, TBOrderBean.class);
                tbAdapter = new TBAdapter(mContext, orderBeans, R.layout.rv_order_list);
                if (getView() != null) {
                    getView().loadTB(tbAdapter);
                }

                for (int i = 0; i < orderBeans.size(); i++) {
                    getTbPic(orderBeans.get(i), i);
                }

                tbAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                            currentTime = System.currentTimeMillis();
                            ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                    .withString("para", orderBeans.get(position).getNumIid())
                                    .withString("shoptype", "淘宝".equals(orderBeans.get(position).getOrderType()) ? "1" : "0")
                                    .withString("commission_rate", Double.valueOf(orderBeans.get(position).getTotalCommissionRate()) + "")
                                    .withInt("type", 1)
                                    .navigation();
                        }
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                List<TBOrderBean> orderBeans = new ArrayList<>();
                TBAdapter tbAdapter = new TBAdapter(mContext, orderBeans, R.layout.rv_order_list);
                if (getView() != null) {
                    getView().loadTB(tbAdapter);
                }
            }
        }));
    }

    private void jdOrder() {
        Map map = MapUtil.getInstance().addParms("status", 1).addParms("type", 1).addParms("current", "1").addParms("pageSize", "1000").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_PDD_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("jd已结算：" + result);
                final List<JDOrderBean> jdOrderBeans = JSON.parseArray(result, JDOrderBean.class);

                JDAdapter jdAdapter = new JDAdapter(mContext, jdOrderBeans, R.layout.rv_order_list);
                getView().loadJD(jdAdapter);

                jdAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                            currentTime = System.currentTimeMillis();
                            ARouter.getInstance().build("/module_classify/JDCommodityDetailsActivity")
                                    .withString("skuid", jdOrderBeans.get(position).getSkuId())
                                    .navigation();
                        }
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                List<JDOrderBean> jdOrderBeans = new ArrayList<>();
                JDAdapter jdAdapter = new JDAdapter(mContext, jdOrderBeans, R.layout.rv_order_list);
                getView().loadJD(jdAdapter);

            }
        }));
    }

    private void pddOrder() {
        Map map = MapUtil.getInstance().addParms("status", 1).addParms("type", 2).addParms("current", "1").addParms("pageSize", "1000").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_PDD_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("pdd已结算：" + result);
                dataList = JSON.parseArray(result, MyOrderBean.class);

                adapter = new RvListAdapter(mContext, dataList, R.layout.rv_order_list);
                if (getView() != null) {
                    getView().loadMineRv(adapter);
                }

                adapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        if ((System.currentTimeMillis() - currentTime) / 1000 >= 3) {
                            currentTime = System.currentTimeMillis();
                            ARouter.getInstance().build("/module_classify/CommodityDetailsActivity")
                                    .withString("goods_id", dataList.get(position).getGoodsId() + "")
                                    .navigation();
                        }
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                List<MyOrderBean> dataList = new ArrayList<>();
                RvListAdapter adapter = new RvListAdapter(mContext, dataList, R.layout.rv_order_list);
                if (getView() != null) {
                    getView().loadMineRv(adapter);
                }
            }
        }));
    }

    private void getTbPic(TBOrderBean bean, final int position) {
        Map map = MapUtil.getInstance().addParms("num_iid", bean.getNumIid()).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSGETITEMDESC, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("淘宝图片：" + result);
                try {
                    JSONObject jsonObject = JSON.parseObject(result);
                    String info = (String) jsonObject.get("info");

                    if (!TextUtils.isEmpty(info)) {
                        TBGoodsDetailsBean tbGoodsDetailsBean = JSON.parseObject(info, new TypeReference<TBGoodsDetailsBean>() {
                        }.getType());

                        orderBeans.get(flag + position).setImage(tbGoodsDetailsBean.getN_tbk_item().getPict_url());
                        tbAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}

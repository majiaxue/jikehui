package com.example.fans_order.fragment_lose;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.bean.FansOrderBean;
import com.example.bean.JdFansOrderBean;
import com.example.bean.TBGoodsDetailsBean;
import com.example.bean.TbFansOrderBean;
import com.example.common.CommonResource;
import com.example.fans_order.FansOrderActivity;
import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.fans_order.adapter.JdFansAdapter;
import com.example.fans_order.adapter.TbFansAdapter;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class FansLoseOrderPresenter extends BasePresenter<FansLoseOrderView> {
    private List<FansOrderBean> pddList = new ArrayList();
    private FansOrderRvAdapter pddAdapter;

    private List<TbFansOrderBean> tbList = new ArrayList<>();
    private TbFansAdapter tbFansAdapter;

    private List<JdFansOrderBean> jdList = new ArrayList<>();
    private JdFansAdapter jdAdapter;

    private int flag = 0;

    public FansLoseOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page) {
        ProcessDialogUtil.showProcessDialog(mContext);
//        WaitDialog.show((AppCompatActivity)mContext,null);

        if (FansOrderActivity.index == 0) {
//            scOrder(page);
        } else if (FansOrderActivity.index == 1) {
            tbOrder(page);
        } else if (FansOrderActivity.index == 2) {
            jdOrder(page);
        } else if (FansOrderActivity.index == 3) {
            pddOrder(page);
        }
    }

    private void pddOrder(final int page) {
        Map map = MapUtil.getInstance().addParms("currentPage", page).addParms("status", 2).addParms("pageSize", "10").addParms("type", "3").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_FANS_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("pdd粉丝订单shixiao：" + result);
                if (getView() != null) {
                    getView().loadSuccess();
                }

                List<FansOrderBean> baseEntity = JSON.parseArray(result, FansOrderBean.class);
                if (page == 1) {
                    pddList.clear();
                }
                pddList.addAll(baseEntity);
                if (pddAdapter == null) {
                    pddAdapter = new FansOrderRvAdapter(mContext, pddList, R.layout.rv_fans_order_list);
                    if (getView() != null) {
                        getView().loadFansRv(pddAdapter);
                    }
                } else {
                    if (getView() != null) {
                        getView().loadFansRv(pddAdapter);
                    }
                }
//                pddAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(RecyclerView parent, View view, int position) {
//                        ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").withString("goods_id", pddList.get(position).getGoodsId() + "").navigation();
//                    }
//                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                tbFansAdapter = new TbFansAdapter(mContext, tbList, R.layout.rv_fans_order_list);
                if (getView() != null) {
                    getView().loadTb(tbFansAdapter);
                    getView().loadSuccess();
                }
            }
        }));
    }

    private void jdOrder(final int page) {
        Map map = MapUtil.getInstance().addParms("currentPage", page).addParms("status", 2).addParms("pageSize", "10").addParms("type", "2").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_FANS_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("jd粉丝订单shixiao：" + result);
                if (getView() != null) {
                    getView().loadSuccess();
                }
                if (page == 1) {
                    jdList.clear();
                }
                jdList.addAll(JSON.parseArray(result, JdFansOrderBean.class));
                if (jdAdapter == null) {
                    jdAdapter = new JdFansAdapter(mContext, jdList, R.layout.rv_fans_order_list);
                    if (getView() != null) {
                        getView().loadJd(jdAdapter);
                    }
                } else {
                    if (getView() != null) {
                        getView().loadJd(jdAdapter);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                jdAdapter = new JdFansAdapter(mContext, jdList, R.layout.rv_fans_order_list);
                if (getView() != null) {
                    getView().loadJd(jdAdapter);
                    getView().loadSuccess();
                }
            }
        }));
    }

    private void tbOrder(final int page) {
        Map map = MapUtil.getInstance().addParms("currentPage", page).addParms("status", 2).addParms("pageSize", "10").addParms("type", "1").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_FANS_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("tb粉丝订单shixiao：" + result);
                try {
                    if (getView() != null) {
                        getView().loadSuccess();
                    }
                    if (page == 1) {
                        tbList.clear();
                    }
                    flag = tbList.size();
                    List<TbFansOrderBean> orderBeans = JSON.parseArray(result, TbFansOrderBean.class);
                    tbList.addAll(orderBeans);

                    for (int i = 0; i < orderBeans.size(); i++) {
                        getTbPic(orderBeans.get(i), i);
                    }

                    tbFansAdapter = new TbFansAdapter(mContext, tbList, R.layout.rv_fans_order_list);
                    if (getView() != null) {
                        getView().loadTb(tbFansAdapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                tbFansAdapter = new TbFansAdapter(mContext, tbList, R.layout.rv_fans_order_list);
                if (getView() != null) {
                    getView().loadTb(tbFansAdapter);
                    getView().loadSuccess();
                }
            }
        }));
    }

    private void getTbPic(TbFansOrderBean bean, final int position) {
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
                        if (getView() != null) {
                            tbList.get(flag + position).setImage(tbGoodsDetailsBean.getN_tbk_item().getPict_url());
                            tbFansAdapter.notifyDataSetChanged();
                            getView().moveTo(flag);
                        }
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

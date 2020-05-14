package com.example.universallist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.HotRecommendBean;
import com.example.bean.TBGoodsRecBean;
import com.example.bean.UniversalListBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.universallist.adapter.BaoYouAdapter;
import com.example.universallist.adapter.HotRecommendRecAdapter;
import com.example.universallist.adapter.UniversalListRecAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.MyTimeUtil;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class UniversalListPresenter extends BasePresenter<UniversalListView> {

    private String itemType;
    private List<UniversalListBean.DataBean.ListBean> dataBeanList = new ArrayList<>();
    private List<HotRecommendBean.DataBean> hotList = new ArrayList<>();
    private List<TBGoodsRecBean.ResultListBean> tbList = new ArrayList();
    private BaoYouAdapter baoYouAdapter;
    private HotRecommendRecAdapter hotRecommendRecAdapter;
    private UniversalListRecAdapter universalListRecAdapter;

    public UniversalListPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void universalList(int position, final int page) {
        if (position == 1) {
            //淘抢购
            itemType = "taoQiangGou";
        } else if (position == 3) {
            //聚划算
            itemType = "juHuaSuan";
        }
        ProcessDialogUtil.showProcessDialog(mContext);
        Map map = MapUtil.getInstance().addParms("pageNum", page).addParms(itemType, 1).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.GETGOODSLIST, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("UniversalListPresenterResult" + result);
                UniversalListBean universalListBean = JSON.parseObject(result, new TypeReference<UniversalListBean>() {
                }.getType());
                if (universalListBean != null && universalListBean.getData() != null) {
                    if (page == 1) {
                        dataBeanList.clear();
                    }
                    dataBeanList.addAll(universalListBean.getData().getList());
                    if (universalListRecAdapter == null) {
                        universalListRecAdapter = new UniversalListRecAdapter(mContext, dataBeanList, R.layout.item_universal_list_rec);
                        if (getView() != null) {
                            getView().loadData(universalListRecAdapter);
                        }
                    } else {
                        universalListRecAdapter.notifyItemChanged(20);
                    }
                    universalListRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            if (TextUtils.isEmpty(SPUtil.getToken())) {
                                PopUtils.isLogin(mContext);
                            } else {
                                ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                        .withString("para", dataBeanList.get(position).getGoodsId())
                                        .withString("shoptype", "1")
                                        .withDouble("youhuiquan", dataBeanList.get(position).getCouponPrice())
                                        .withString("coupon_start_time", dataBeanList.get(position).getCouponStartTime())
                                        .withString("coupon_end_time", dataBeanList.get(position).getCouponEndTime())
                                        .withString("commission_rate", dataBeanList.get(position).getCommissionRate() + "")
                                        .withInt("type", 0)
                                        .navigation();
                            }
                        }
                    });
                }
                if (getView() != null) {
                    getView().finishRefresh();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
//                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().finishRefresh();
                }
            }
        }));
    }

    public void hotRecommend(final int page, int type) {
        ProcessDialogUtil.showProcessDialog(mContext);
        Map map = MapUtil.getInstance().addParms("sale_type", type).addParms("min_id", page).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSSALESLIST, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("hotRecommend：" + result);
                HotRecommendBean hotRecommendBean = JSON.parseObject(result, new TypeReference<HotRecommendBean>() {
                }.getType());
                if (hotRecommendBean != null && hotRecommendBean.getData().size() != 0) {
                    if (page == 1) {
                        hotList.clear();
                    }
                    hotList.addAll(hotRecommendBean.getData());
                    if (hotRecommendRecAdapter == null) {
                        hotRecommendRecAdapter = new HotRecommendRecAdapter(mContext, hotList, R.layout.item_universal_list_rec);
                        if (getView() != null) {
                            getView().loadData(hotRecommendRecAdapter);
                        }
                    } else {
                        hotRecommendRecAdapter.notifyItemChanged(20);
                    }
                    hotRecommendRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            if (!TextUtils.isEmpty(SPUtil.getToken())) {
                                String startTime = MyTimeUtil.date2String(hotList.get(position).getCouponstarttime() + "000");
                                String endTime = MyTimeUtil.date2String(hotList.get(position).getCouponendtime() + "000");
                                ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                        .withString("para", hotList.get(position).getItemid())
                                        .withString("shoptype", "1")
                                        .withDouble("youhuiquan", Double.valueOf(hotList.get(position).getCouponmoney()))
                                        .withString("coupon_start_time", startTime)
                                        .withString("coupon_end_time", endTime)
                                        .withString("commission_rate", hotList.get(position).getTkrates())
                                        .withInt("type", 0)
                                        .navigation();
                            } else {
                                //是否登录
                                PopUtils.isLogin(mContext);
                            }

                        }
                    });

                }
                if (getView() != null) {
                    getView().finishRefresh();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                if (getView() != null) {
                    getView().finishRefresh();
                }
            }
        }));
    }


    public void baoyou(final int page) {
        ProcessDialogUtil.showProcessDialog(mContext);
        Map build = MapUtil.getInstance().addParms("page", page).addParms("pagesize", 20).addParms("para", "9.9").addParms("start_price", "9").addParms("end_price", "10").build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.SEARCH_NEW_TB, build);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("UniversalListPresenter9.9包邮" + result);
                JSONObject jsonObject = JSON.parseObject(result);
                if (result != null) {
                    if ("0".equals(jsonObject.getString("error"))) {
                        String search_type = jsonObject.getString("search_type");
                        if ("1".equals(search_type)) {
                            JSONArray resultList = jsonObject.getJSONArray("result_list");
                            if (page == 1) {
                                tbList.clear();
                            }
                            for (int i = 0; i < resultList.size(); i++) {
                                TBGoodsRecBean.ResultListBean dataBean = new TBGoodsRecBean.ResultListBean();
                                JSONObject jsonObject1 = resultList.getJSONObject(i);
                                dataBean.setItem_id(jsonObject1.getString("item_id"));
                                dataBean.setPict_url(jsonObject1.getString("pict_url"));
                                dataBean.setTitle(jsonObject1.getString("title"));
                                dataBean.setCommission_rate(jsonObject1.getString("commission_rate"));
                                dataBean.setVolume(jsonObject1.getString("volume"));
                                dataBean.setCoupon_amount(jsonObject1.getString("coupon_amount"));
                                dataBean.setUser_type(jsonObject1.getString("user_type"));
                                dataBean.setZk_final_price(jsonObject1.getString("zk_final_price"));
                                dataBean.setReserve_price(jsonObject1.getString("reserve_price"));
                                dataBean.setTk_total_sales(jsonObject1.getString("tk_total_sales"));
                                dataBean.setCoupon_start_time(jsonObject1.getString("coupon_start_time"));
                                dataBean.setCoupon_end_time(jsonObject1.getString("coupon_end_time"));
                                dataBean.setCommission_rate(jsonObject1.getString("commission_rate"));
                                tbList.add(dataBean);
                            }
                        }
                    }
                    if (baoYouAdapter == null) {
                        baoYouAdapter = new BaoYouAdapter(mContext, tbList, R.layout.item_universal_list_rec);
                        if (getView() != null) {
                            getView().loadData(baoYouAdapter);
                        }
                    } else {
                        baoYouAdapter.notifyItemChanged(20);
                    }

                    baoYouAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            if (TextUtils.isEmpty(SPUtil.getToken())) {
                                PopUtils.isLogin(mContext);
                            } else {
                                ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                        .withString("para", tbList.get(position).getItem_id())
                                        .withString("shoptype", "1")
                                        .withDouble("youhuiquan", Double.valueOf(tbList.get(position).getCoupon_amount()))
                                        .withString("coupon_start_time", tbList.get(position).getCoupon_start_time())
                                        .withString("coupon_end_time", tbList.get(position).getCoupon_end_time())
                                        .withString("commission_rate", tbList.get(position).getCommission_rate())
                                        .withInt("type", 1)
                                        .navigation();
                            }
                        }
                    });
                }

                if (getView() != null) {
                    getView().finishRefresh();
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("UniversalListPresenter9.9包邮errorMsg" + errorMsg);
                if (getView() != null) {
                    getView().finishRefresh();
                }
            }
        }));

    }
}

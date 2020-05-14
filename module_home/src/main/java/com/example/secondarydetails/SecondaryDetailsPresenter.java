package com.example.secondarydetails;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.SecondaryJDRecAdapter;
import com.example.adapter.SecondaryPddRecAdapter;
import com.example.bean.JDListBean;
import com.example.bean.JDTabBean;
import com.example.bean.PddGoodsSearchVo;
import com.example.bean.SecondaryPddRecBean;
import com.example.bean.SecondaryTabBean;
import com.example.bean.TBGoodsRecBean;
import com.example.bean.TBGoodsSearchBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.secondarydetails.adapter.SecondaryTBRecAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/31
 * Describe:
 */
public class SecondaryDetailsPresenter extends BasePresenter<SecondaryDetailsView> {

    private int page = 1;
    private List<SecondaryTabBean.GoodsOptGetResponseBean.GoodsOptListBean> catsListBeans = new ArrayList<>();
    private List<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> baseRecBeanList = new ArrayList<>();
    private List<TBGoodsSearchBean> tBGoodsSearchBeans = new ArrayList<>();
    private List<TBGoodsRecBean.ResultListBean> tbGoodsList = new ArrayList<>();
    private List<JDTabBean.DataBean> jdTabList = new ArrayList<>();
    private List<JDListBean.DataBean> listsBeanList = new ArrayList<>();
    private SecondaryTBRecAdapter secondaryTBRecAdapter;
    private SecondaryPddRecAdapter baseRecAdapter;
    private SecondaryJDRecAdapter secondaryJDRecAdapter;
    private SmartRefreshLayout smartRefresh;
    private int currentCount = 0;   //当前数据总数

    public SecondaryDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(final TabLayout secondaryDetailsTab, final SmartRefreshLayout secondaryDetailsSmartRefresh, final String type) {
        smartRefresh = secondaryDetailsSmartRefresh;
        ProcessDialogUtil.showProcessDialog(mContext);
        if (type.equals("2")) {
            //拼多多
            Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.GOODSCATS);
            RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("SecondaryDetailsTabResult------------------>" + result);
                    SecondaryTabBean secondaryTabBean = JSON.parseObject(result, new TypeReference<SecondaryTabBean>() {
                    }.getType());
                    if (secondaryTabBean != null) {
                        if (secondaryTabBean.getGoods_opt_get_response() != null && secondaryTabBean.getGoods_opt_get_response().getGoods_opt_list().size() != 0) {
                            catsListBeans.clear();
                            catsListBeans.addAll(secondaryTabBean.getGoods_opt_get_response().getGoods_opt_list());

                            for (int i = 0; i < catsListBeans.size(); i++) {
                                secondaryDetailsTab.addTab(secondaryDetailsTab.newTab().setText(catsListBeans.get(i).getOpt_name()));
                            }

                            initTabIndicator(secondaryDetailsTab);
                            secondaryDetailsTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    //拼多多,淘宝,京东,page用来刷新,type用来分辨
                                    page = 1;
                                    initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, tab.getPosition());
                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });
                            //拼多多,淘宝,京东,page用来刷新,type用来分辨
                            initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, 0);
                        } else {
                            LogUtil.e("数据为空");
                        }

                    } else {
                        LogUtil.e("数据为空");
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsTabErrorMsg------------------>" + errorMsg);

                }
            }));
        } else if (type.equals("0") || type.equals("5")) {
            //淘宝
            Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.TBKGOODSTBCATEGOTY);
            RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsResult淘宝--------------->" + result);
                    tBGoodsSearchBeans = JSON.parseArray(result, TBGoodsSearchBean.class);
                    if (tBGoodsSearchBeans != null) {
                        if (tBGoodsSearchBeans != null && tBGoodsSearchBeans.size() != 0) {
                            if (type.equals("2")) {
                                TBGoodsSearchBean tbGoodsSearchBean = tBGoodsSearchBeans.get(0);
                                TBGoodsSearchBean tbGoodsSearchBean1 = tBGoodsSearchBeans.get(4);
                                tBGoodsSearchBeans.remove(4);
                                tBGoodsSearchBeans.remove(0);
                                tBGoodsSearchBeans.set(0, tbGoodsSearchBean1);
                                tBGoodsSearchBeans.set(2, tbGoodsSearchBean);
                            }

                            for (int i = 0; i < tBGoodsSearchBeans.size(); i++) {
                                secondaryDetailsTab.addTab(secondaryDetailsTab.newTab().setText(tBGoodsSearchBeans.get(i).getCat_name()));
                            }

                            initTabIndicator(secondaryDetailsTab);

                            secondaryDetailsTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    ProcessDialogUtil.showProcessDialog(mContext);
                                    tbGoodsList.clear();
                                    page = 1;
                                    //拼多多,淘宝,京东,page用来刷新,type用来分辨
                                    initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, tab.getPosition());

                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });
                            //拼多多,淘宝,京东,page用来刷新,type用来分辨
                            initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, 0);

                        } else {
                            LogUtil.e("数据为空");
                        }
                    } else {
                        LogUtil.e("数据为空");
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsErrorMsg淘宝--------------->" + errorMsg);
                }
            }));

        } else if (type.equals("3")) {
            //京东
            final Map map = MapUtil.getInstance().addParms("grade", 0).addParms("parentId", 0).build();
            Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.JDGETCATEGORY, map);
            RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsResult京东--------------->" + result);
                    JDTabBean jdTabBeans = JSON.parseObject(result, new TypeReference<JDTabBean>() {
                    }.getType());
                    if (jdTabBeans != null) {
                        if (jdTabBeans.getData() != null && jdTabBeans.getData().size() != 0) {
                            jdTabList.addAll(jdTabBeans.getData());
                            for (int i = 0; i < jdTabList.size(); i++) {
                                secondaryDetailsTab.addTab(secondaryDetailsTab.newTab().setText(jdTabList.get(i).getName()));
                            }

                            initTabIndicator(secondaryDetailsTab);

                            secondaryDetailsTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    ProcessDialogUtil.showProcessDialog(mContext);
                                    //拼多多,淘宝,京东,page用来刷新,type用来分辨
                                    page = 1;
                                    listsBeanList.clear();
                                    initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, tab.getPosition());

                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });

                            //拼多多,淘宝,京东,page用来刷新,type用来分辨
                            initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, 0);

                        } else {
                            LogUtil.e("空");
                        }

                    } else {
                        LogUtil.e("空");
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsErrorMsg京东--------------->" + errorMsg);
                }
            }));
        }

        secondaryDetailsSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                //拼多多,淘宝,京东,page用来刷新,type用来分辨
                try {
                    initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, secondaryDetailsTab.getTabAt(secondaryDetailsTab.getSelectedTabPosition()).getPosition());
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                refreshlayout.finishRefresh();
            }
        });
        secondaryDetailsSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                //拼多多,淘宝,京东,page用来刷新,type用来分辨
                initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, secondaryDetailsTab.getTabAt(secondaryDetailsTab.getSelectedTabPosition()).getPosition());
//                refreshlayout.finishLoadMore();
            }
        });
    }

    //京东

    private void initList(List<SecondaryTabBean.GoodsOptGetResponseBean.GoodsOptListBean> catsListBeans, List<TBGoodsSearchBean> tBGoodsSearchBeans, List<JDTabBean.DataBean> jdTabList, final int page, final String type, int position) {
        //0淘宝 2 拼多多  4京东 6天猫
        if ("2".equals(type)) {
            LogUtil.e("------------------------------->" + catsListBeans.get(position).getOpt_id());
            PddGoodsSearchVo pddGoodsSearchVo = new PddGoodsSearchVo();
            pddGoodsSearchVo.setPage(page);
            pddGoodsSearchVo.setOptId((long) catsListBeans.get(position).getOpt_id());
            String pddGoodsSearchVoStr = JSON.toJSONString(pddGoodsSearchVo);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
            Observable pddGoods = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postDataWithBody(CommonResource.PDDGOODS, body);
            RetrofitUtil.getInstance().toSubscribe(pddGoods, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    smartRefresh.finishLoadMore();
                    smartRefresh.finishRefresh();
//                    CacheManager.clearDiskCache(mContext);
                    LogUtil.e("SecondaryDetailsResult----------->" + result);
                    SecondaryPddRecBean secondaryPddRecBean = JSON.parseObject(result, new TypeReference<SecondaryPddRecBean>() {
                    }.getType());
                    if (secondaryPddRecBean != null) {
                        if (!"0".equals(secondaryPddRecBean.getGoods_search_response().getTotal_count())) {
                            if (page == 1) {
                                baseRecBeanList.clear();
                            }
                            baseRecBeanList.addAll(secondaryPddRecBean.getGoods_search_response().getGoods_list());
                            if (baseRecAdapter == null) {
                                baseRecAdapter = new SecondaryPddRecAdapter(mContext, baseRecBeanList, R.layout.item_base_rec);
                                if (getView() != null) {
                                    getView().lodeRec(baseRecAdapter);
                                }
                            } else {
                                if (page != 1) {
                                    baseRecAdapter.notifyItemChanged(20);
                                } else {
                                    baseRecAdapter.notifyDataSetChanged();
                                }
                            }

                            baseRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(RecyclerView parent, View view, int position) {
                                    long goods_id = baseRecBeanList.get(position).getGoods_id();
                                    ARouter.getInstance()
                                            .build("/module_classify/CommodityDetailsActivity")
                                            .withString("goods_id", goods_id + "")
                                            .navigation();
                                }
                            });

                            baseRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                                @Override
                                public void ViewOnClick(View view, final int index) {
                                    view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            long goods_id = baseRecBeanList.get(index).getGoods_id();
                                            ARouter.getInstance()
                                                    .build("/module_classify/CommodityDetailsActivity")
                                                    .withString("goods_id", goods_id + "")
                                                    .navigation();
                                        }
                                    });
                                }
                            });
                        } else {
                            if (getView() != null) {
                                getView().noGoods(true);
                            }
                            LogUtil.e("尚无数据");
                        }
                    } else {
                        LogUtil.e("尚无数据");
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsError----------->" + errorMsg);
                    smartRefresh.finishLoadMore();
                    smartRefresh.finishRefresh();
                    baseRecBeanList.clear();
                    if (baseRecAdapter == null) {
                        baseRecAdapter = new SecondaryPddRecAdapter(mContext, baseRecBeanList, R.layout.item_base_rec);
                        if (getView() != null) {
                            getView().noGoods(true);
//                            getView().lodeRec(baseRecAdapter);
                        }
                    } else {
                        baseRecAdapter.notifyDataSetChanged();
                    }
                    ProcessDialogUtil.dismissDialog();
                }

            }));
        } else if ("0".equals(type)) {
            //淘宝
            Map map = MapUtil.getInstance().addParms("para", tBGoodsSearchBeans.get(position).getCat_name()).addParms("page", page).addParms("istmall", false).build();
            Observable<ResponseBody> dataWithout1 = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.SEARCH_NEW_TB, map);
            RetrofitUtil.getInstance().toSubscribe(dataWithout1, new OnTripartiteCallBack(new OnDataListener() {

                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    smartRefresh.finishLoadMore();
                    smartRefresh.finishRefresh();
//                    CacheManager.clearDiskCache(mContext);
                    LogUtil.e("SecondaryDetailsResult淘宝商品--------------->" + result);
                    try {
                        JSONObject jsonObject = JSON.parseObject(result);
                        if ("0".equals(jsonObject.getString("error"))) {
                            if (getView() != null) {
                                getView().noGoods(false);
                            }
                            if (page == 1) {
                                tbGoodsList.clear();
                            }
                            JSONArray data = jsonObject.getJSONArray("result_list");
                            for (int i = 0; i < data.size(); i++) {
                                TBGoodsRecBean.ResultListBean dataBean = new TBGoodsRecBean.ResultListBean();
                                JSONObject jsonObject1 = data.getJSONObject(i);
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
                                tbGoodsList.add(dataBean);
                            }
//                            tbGoodsList.addAll(tbGoodsRecBean.getData());

                            if (secondaryTBRecAdapter == null) {
                                secondaryTBRecAdapter = new SecondaryTBRecAdapter(mContext, tbGoodsList, R.layout.item_base_rec);
                                if (getView() != null) {
                                    getView().lodeTBRec(secondaryTBRecAdapter);
                                }
                            } else {
                                if (page != 1) {
                                    secondaryTBRecAdapter.notifyItemChanged(20);
                                } else {
                                    secondaryTBRecAdapter.notifyDataSetChanged();
                                }
                            }

                            secondaryTBRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(RecyclerView parent, View view, int position) {
                                    if (!TextUtils.isEmpty(SPUtil.getToken())) {

                                        ARouter.getInstance()
                                                .build("/module_classify/TBCommodityDetailsActivity")
                                                .withString("para", tbGoodsList.get(position).getItem_id())
                                                .withString("shoptype", tbGoodsList.get(position).getUser_type())
                                                .withDouble("youhuiquan", Double.valueOf(tbGoodsList.get(position).getCoupon_amount()))
                                                .withString("coupon_start_time", tbGoodsList.get(position).getCoupon_start_time())
                                                .withString("coupon_end_time", tbGoodsList.get(position).getCoupon_end_time())
                                                .withString("commission_rate", tbGoodsList.get(position).getCommission_rate())
                                                .withInt("type", 1)
                                                .navigation();
                                    } else {
                                        //是否登录
                                        PopUtils.isLogin(mContext);
                                    }

                                }
                            });

                        } else {
                            if (getView() != null) {
                                getView().noGoods(true);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    ProcessDialogUtil.dismissDialog();
                    smartRefresh.finishLoadMore();
                    smartRefresh.finishRefresh();
                    tbGoodsList.clear();
                    if (secondaryTBRecAdapter == null) {
                        secondaryTBRecAdapter = new SecondaryTBRecAdapter(mContext, tbGoodsList, R.layout.item_base_rec);
                        if (getView() != null) {
                            getView().noGoods(true);
//                            getView().lodeTBRec(secondaryTBRecAdapter);
                        }
                    } else {
                        secondaryTBRecAdapter.notifyDataSetChanged();
                    }
                    LogUtil.e("SecondaryDetailsErrorMsg淘宝商品--------------->" + errorMsg);
                }
            }));
        } else if ("5".equals(type)) {
            //天猫
            Map map = MapUtil.getInstance().addParms("para", tBGoodsSearchBeans.get(position).getCat_name()).addParms("page", page).addParms("istmall", true).build();
            Observable<ResponseBody> dataWithout1 = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.SEARCH_NEW_TB, map);
            RetrofitUtil.getInstance().toSubscribe(dataWithout1, new OnTripartiteCallBack(new OnDataListener() {

//                private TBGoodsRecBean tbGoodsRecBean;

                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    smartRefresh.finishLoadMore();
                    smartRefresh.finishRefresh();
                    LogUtil.e("SecondaryDetailsResult淘宝商品--------------->" + result);
                    try {
                        JSONObject jsonObject = JSON.parseObject(result);
                        if ("0".equals(jsonObject.getString("error"))) {
                            if (getView() != null) {
                                getView().noGoods(false);
                            }
                            if (page == 1) {
                                tbGoodsList.clear();
                            }
                            JSONArray data = jsonObject.getJSONArray("result_list");
                            for (int i = 0; i < data.size(); i++) {
                                TBGoodsRecBean.ResultListBean dataBean = new TBGoodsRecBean.ResultListBean();
                                JSONObject jsonObject1 = data.getJSONObject(i);
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
                                tbGoodsList.add(dataBean);
                            }
//                            tbGoodsList.addAll(tbGoodsRecBean.getData());

                            if (secondaryTBRecAdapter == null) {
                                secondaryTBRecAdapter = new SecondaryTBRecAdapter(mContext, tbGoodsList, R.layout.item_base_rec);
                                if (getView() != null) {
                                    getView().lodeTBRec(secondaryTBRecAdapter);
                                }
                            } else {
                                if (page != 1) {
                                    secondaryTBRecAdapter.notifyItemChanged(20);
                                } else {
                                    secondaryTBRecAdapter.notifyDataSetChanged();
                                }
                            }

                            secondaryTBRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(RecyclerView parent, View view, int position) {
                                    if (!TextUtils.isEmpty(SPUtil.getToken())) {

                                        ARouter.getInstance()
                                                .build("/module_classify/TBCommodityDetailsActivity")
                                                .withString("para", tbGoodsList.get(position).getItem_id())
                                                .withString("shoptype", tbGoodsList.get(position).getUser_type())
                                                .withDouble("youhuiquan", Double.valueOf(tbGoodsList.get(position).getCoupon_amount()))
                                                .withString("coupon_start_time", tbGoodsList.get(position).getCoupon_start_time())
                                                .withString("coupon_end_time", tbGoodsList.get(position).getCoupon_end_time())
                                                .withString("commission_rate", tbGoodsList.get(position).getCommission_rate())
                                                .withInt("type", 1)
                                                .navigation();
                                    } else {
                                        //是否登录
                                        PopUtils.isLogin(mContext);
                                    }

                                }
                            });

                        } else {
                            if (getView() != null) {
                                getView().noGoods(true);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    ProcessDialogUtil.dismissDialog();
                    smartRefresh.finishLoadMore();
                    smartRefresh.finishRefresh();
                    tbGoodsList.clear();
                    if (secondaryTBRecAdapter == null) {
                        secondaryTBRecAdapter = new SecondaryTBRecAdapter(mContext, tbGoodsList, R.layout.item_base_rec);
                        if (getView() != null) {
                            getView().noGoods(true);
//                            getView().lodeTBRec(secondaryTBRecAdapter);
                        }
                    } else {
                        secondaryTBRecAdapter.notifyDataSetChanged();
                    }
                    LogUtil.e("SecondaryDetailsErrorMsg淘宝商品--------------->" + errorMsg);
                }
            }));
        } else if (type.equals("3")) {
            //京东
            Map build = MapUtil.getInstance().addParms("isCoupon", 1).addParms("pageIndex", page).addParms("pageSize", 10).addParms("keyword", "").build();
            if (jdTabList.get(position).getGrade() == 0) {
                build.put("cid1", jdTabList.get(position).getId());
            } else if (jdTabList.get(position).getGrade() == 1) {
                build.put("cid2", jdTabList.get(position).getId());
            } else if (jdTabList.get(position).getGrade() == 2) {
                build.put("cid3", jdTabList.get(position).getId());
            }
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.JDGOODSLIST, build);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("SecondaryDetailsResult京东商品--------------->" + result);
                    ProcessDialogUtil.dismissDialog();
                    smartRefresh.finishLoadMore();
                    smartRefresh.finishRefresh();
                    try {
                        org.json.JSONObject jsonObject = new org.json.JSONObject(result);
                        String code = jsonObject.optString("code");
                        if ("-1".equals(code)) {
                            listsBeanList.clear();
                            Toast.makeText(mContext, "获取数据失败", Toast.LENGTH_SHORT).show();
                        } else if ("200".equals(code)) {
//                            CacheManager.clearDiskCache(mContext);
                            final JDListBean jDGoodsRecBean = JSON.parseObject(result, JDListBean.class);

                            if (jDGoodsRecBean != null) {
                                if (jDGoodsRecBean.getCode() == -1) {
                                    if (page == 1) {
                                        if (getView() != null) {
                                            getView().noGoods(true);
                                        }
                                    }
                                } else {
                                    if (getView() != null) {
                                        getView().noGoods(false);
                                    }
                                    if (page == 1) {
                                        listsBeanList.clear();
                                    }
                                    currentCount = listsBeanList.size();
                                    listsBeanList.addAll(jDGoodsRecBean.getData());
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (secondaryJDRecAdapter == null) {
                        secondaryJDRecAdapter = new SecondaryJDRecAdapter(mContext, listsBeanList, R.layout.item_base_rec);
                        if (getView() != null) {
                            getView().lodeJDRec(secondaryJDRecAdapter);
                        }
                    } else {
                        if (page != 1) {
                            secondaryJDRecAdapter.notifyItemChanged(20);
                        } else {
                            secondaryJDRecAdapter.notifyDataSetChanged();
                        }
                    }

                    secondaryJDRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance()
                                    .build("/module_classify/JDCommodityDetailsActivity")
                                    .withString("skuid", listsBeanList.get(position).getSkuId() + "")
                                    .navigation();
                        }
                    });
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    smartRefresh.finishLoadMore();
                    smartRefresh.finishRefresh();
                    LogUtil.e("SecondaryDetailsErrorMsg京东商品--------------->" + errorMsg);
                    ProcessDialogUtil.dismissDialog();
                    listsBeanList.clear();
                    if (secondaryJDRecAdapter == null) {
                        secondaryJDRecAdapter = new SecondaryJDRecAdapter(mContext, listsBeanList, R.layout.item_base_rec);
                        if (getView() != null) {
                            getView().noGoods(true);
//                            getView().lodeJDRec(secondaryJDRecAdapter);
                        }
                    } else {
                        secondaryJDRecAdapter.notifyDataSetChanged();
                    }
                }
            }));
        }

    }


    //设置tablayout指示器与文字一样宽
    private void initTabIndicator(final TabLayout secondaryDetailsTab) {
        secondaryDetailsTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) secondaryDetailsTab.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });
    }
}

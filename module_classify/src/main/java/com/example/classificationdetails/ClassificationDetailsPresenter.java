package com.example.classificationdetails;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.BaseRecAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.SecondaryJDRecAdapter;
import com.example.adapter.SecondaryPddRecAdapter;
import com.example.bean.JDGoodsRecBean;
import com.example.bean.JDListBean;
import com.example.bean.PddGoodsSearchVo;
import com.example.bean.SecondaryPddRecBean;
import com.example.bean.TBGoodsRecBean;
import com.example.classificationdetails.adapter.ClassificationRecAdapter;
import com.example.classificationdetails.adapter.JdWaterfallAdapter;
import com.example.classificationdetails.adapter.PddWaterAdapter;
import com.example.common.CommonResource;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.kongzue.dialog.v3.WaitDialog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/20
 * Describe:
 */
public class ClassificationDetailsPresenter extends BasePresenter<ClassificationDetailsView> {

    private List<TBGoodsRecBean.ResultListBean> tbList = new ArrayList<>();
    private List<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> pddList = new ArrayList<>();
    private List<JDListBean.DataBean> jdList = new ArrayList<>();
    private String[] titleArr = {"淘宝", "拼多多", "京东"};
    private BaseRecAdapter lstAdapter;
    private boolean isWaterfall = false;
    private ClassificationRecAdapter waterfallAdapter;
    private SecondaryPddRecAdapter pddLstAdapter;
    private PddWaterAdapter pddWaterAdapter;
    private SecondaryJDRecAdapter jdLstAdapter;
    private JdWaterfallAdapter jdWaterfallAdapter;

    private int goodsType = 1;  //商品来源  1：淘宝  2：拼多多   3：京东
    private String content = "";     //搜索内容

    private int sort_type = 1;  //拼多多排序字段

    private boolean isSynthesize = true;        //是否综合排序
    private boolean synthesizeTemp = true;      //当前是否为综合排序

    private boolean isPositiveSalesVolume = false;  //是否销量排行
    private boolean isSalesVolumeReduce = false;     //是否销量从高到低
    private boolean saleVolumTemp = false;

    private boolean isPositivePrice = false;        //是否价格排行
    private boolean isPriceReduce = true;          //是否价格从高到低
    private boolean priceTemp = false;

    private boolean isPositiveCredit = false;       //是否信用排行
    private boolean isCreditReduce = false;          //是否信用从高到低
    private boolean creditTemp = false;

    private int tbNum = 0;
    private int jdNum = 0;
    private int pddNum = 0;


    public ClassificationDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setContent(String content) {
        if (content == null || "".equals(content)) {
            this.content = "衣";
        } else {
            this.content = content;
        }
    }

    public void initTabLayout(final TabLayout classificationTab) {
        for (String title : titleArr) {
            classificationTab.addTab(classificationTab.newTab().setText(title));
        }

        classificationTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) classificationTab.getChildAt(0);

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

    public void searchTB(final int page, String sort) {
        goodsType = 1;
        if (page == 1) {
            ProcessDialogUtil.showProcessDialog(mContext);
//            WaitDialog.show((AppCompatActivity)mContext,null);

        }
        Map map = MapUtil.getInstance().addParms("para", content).addParms("page", page).build();
        if (sort != null) {
            map.put("sort", sort);
        }

        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHead(CommonResource.SEARCH_NEW_TB, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
//                ProcessDialogUtil.dismissDialog();
                LogUtil.e("淘宝搜索：" + result);
                if (getView() != null) {
                    getView().loadFinish();
                }

                try {
                    if (result != null) {
                        if (page == 1) {
                            tbList.clear();
                        }

                        tbNum = tbList.size();
                        JSONObject jsonObject = JSON.parseObject(result);
                        if ("0".equals(jsonObject.getString("error"))) {
                            String search_type = jsonObject.getString("search_type");
                            if ("1".equals(search_type)) {
                                JSONArray resultList = jsonObject.getJSONArray("result_list");
                                for (int i = 0; i < resultList.size(); i++) {
                                    TBGoodsRecBean.ResultListBean dataBean = new TBGoodsRecBean.ResultListBean();
                                    JSONObject object = resultList.getJSONObject(i);

                                    String coupon_info = object.getString("coupon_info");
                                    if (!TextUtils.isEmpty(coupon_info)) {
                                        String[] split = coupon_info.split("减");
                                        String[] split1 = split[1].split("元");
                                        dataBean.setCoupon_amount(split1[0]);
                                    }
                                    dataBean.setItem_id(object.getString("item_id"));
                                    dataBean.setPict_url(object.getString("pict_url"));
                                    dataBean.setTitle(object.getString("title"));
                                    dataBean.setCommission_rate("" + object.getDouble("commission_rate"));
                                    dataBean.setVolume(object.getString("volume"));
                                    dataBean.setCoupon_amount(object.getString("coupon_amount"));
                                    dataBean.setZk_final_price(object.getString("zk_final_price"));
                                    dataBean.setReserve_price(object.getString("reserve_price"));
                                    dataBean.setTk_total_sales(object.getString("tk_total_sales"));
                                    dataBean.setCoupon_start_time(object.getString("coupon_start_time"));
                                    dataBean.setCoupon_end_time(object.getString("coupon_end_time"));

                                    tbList.add(dataBean);
                                }
                            } else if ("2".equals(search_type)) {
                                JSONObject data = jsonObject.getJSONObject("data");
                                TBGoodsRecBean.ResultListBean dataBean = new TBGoodsRecBean.ResultListBean();
                                String coupon_info = data.getString("coupon_info");
                                if (!TextUtils.isEmpty(coupon_info)) {
                                    String[] split = coupon_info.split("减");
                                    String[] split1 = split[1].split("元");
                                    dataBean.setCoupon_amount(split1[0]);
                                }
                                dataBean.setItem_id(data.getString("num_iid"));
                                dataBean.setPict_url(data.getString("pict_url"));
                                dataBean.setTitle(data.getString("title"));
                                dataBean.setCommission_rate("" + data.getDouble("commission_rate"));
                                dataBean.setVolume(data.getString("volume"));
                                dataBean.setZk_final_price(data.getString("zk_final_price"));
                                dataBean.setReserve_price(data.getString("reserve_price"));
                                dataBean.setCoupon_amount(data.getString("coupon_amount"));
                                dataBean.setTk_total_sales(data.getString("volume"));
                                dataBean.setCoupon_start_time(data.getString("coupon_start_time"));
                                dataBean.setCoupon_end_time(data.getString("coupon_end_time"));
                                tbList.add(dataBean);
                            }


                            if (waterfallAdapter == null) {
                                waterfallAdapter = new ClassificationRecAdapter(mContext, tbList, R.layout.item_classification_rec_grid);
                                lstAdapter = new BaseRecAdapter(mContext, tbList, R.layout.item_base_rec, "0");
                                if (isWaterfall) {
                                    if (getView() != null) {
                                        getView().loadTBWaterfallRv(waterfallAdapter);
                                    }
                                } else {
                                    if (getView() != null) {
                                        getView().loadTBLstRv(lstAdapter);
                                    }
                                }
                            } else {
                                if (page == 1) {
                                    if (isWaterfall) {
                                        if (getView() != null) {
                                            getView().loadTBWaterfallRv(waterfallAdapter);
                                        }
                                    } else {
                                        if (getView() != null) {
                                            getView().loadTBLstRv(lstAdapter);
                                        }
                                    }
                                } else {
                                    if (isWaterfall) {
                                        waterfallAdapter.notifyItemChanged(20);
                                    } else {
                                        lstAdapter.notifyItemChanged(20);
                                    }
                                }
                            }


                            if (lstAdapter != null) {
                                lstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(RecyclerView parent, View view, int position) {
                                        ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                                .withString("para", tbList.get(position).getItem_id())
                                                .withInt("type", 1)
                                                .navigation();
                                    }
                                });
                            }

                            if (waterfallAdapter != null) {
                                waterfallAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(RecyclerView parent, View view, int position) {
                                        ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                                .withString("para", tbList.get(position).getItem_id())
                                                .withInt("type", 1)
                                                .navigation();
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    LogUtil.e("异常信息：" + e.getMessage());
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
//                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().loadFinish();
                }
            }
        }));
    }

    public void searchJD(final int page, String sort, String sortName) {
        goodsType = 3;
        if (page == 1) {
            ProcessDialogUtil.showProcessDialog(mContext);
//            WaitDialog.show((AppCompatActivity)mContext,null);

        }

        Map map = MapUtil.getInstance().addParms("keyword", content).addParms("pageIndex", page).addParms("pageSize", "10").addParms("isCoupon", "1").build();
        if (sort != null) {
            map.put("sort", sort);
            map.put("sortName", sortName);
        }
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHead(CommonResource.SEARCHJDGOODS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new DisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().loadFinish();
                }
                try {
                    String string = responseBody.string();
                    LogUtil.e("京东搜索:" + string);
                    final JDListBean jdSearchBean = JSON.parseObject(string, JDListBean.class);
                    if (page == 1) {
                        jdList.clear();
                    }
                    jdList.addAll(jdSearchBean.getData());

                    if (jdWaterfallAdapter == null) {
                        jdWaterfallAdapter = new JdWaterfallAdapter(mContext, jdList, R.layout.item_classification_rec_grid);
                        jdLstAdapter = new SecondaryJDRecAdapter(mContext, jdList, R.layout.item_base_rec);

                        if (isWaterfall) {
                            if (getView() != null) {
                                getView().loadJDWaterfallRv(jdWaterfallAdapter);
                            }
                        } else {
                            if (getView() != null) {
                                getView().loadJDLstRv(jdLstAdapter);
                            }
                        }
                    } else {
                        if (page == 1) {
                            if (isWaterfall) {
                                if (getView() != null) {
                                    getView().loadJDWaterfallRv(jdWaterfallAdapter);
                                }
                            } else {
                                if (getView() != null) {
                                    getView().loadJDLstRv(jdLstAdapter);
                                }
                            }
                        } else {
                            if (isWaterfall) {
                                jdWaterfallAdapter.notifyItemChanged(10);
                            } else {
                                jdLstAdapter.notifyItemChanged(10);
                            }
                        }
                    }


                    if (jdLstAdapter != null) {
                        jdLstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                ARouter.getInstance()
                                        .build("/module_classify/JDCommodityDetailsActivity")
                                        .withString("skuid", jdList.get(position).getSkuId() + "")
                                        .navigation();
                            }
                        });
                    }


                    if (jdWaterfallAdapter != null) {
                        jdWaterfallAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                ARouter.getInstance()
                                        .build("/module_classify/JDCommodityDetailsActivity")
                                        .withString("skuid", jdList.get(position).getSkuId() + "")
                                        .navigation();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().loadFinish();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void searchPDD(final int page) {
        goodsType = 2;
        if (page == 1) {
            ProcessDialogUtil.showProcessDialog(mContext);
//            WaitDialog.show((AppCompatActivity)mContext,null);

        }

        PddGoodsSearchVo pddGoodsSearchVo = new PddGoodsSearchVo();
        pddGoodsSearchVo.setPage(page);
        pddGoodsSearchVo.setKeyword(content);
        pddGoodsSearchVo.setPageSize(10);
        pddGoodsSearchVo.setSortType(sort_type);

        String pddGoodsSearchVoStr = JSON.toJSONString(pddGoodsSearchVo);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postDataWithBody(CommonResource.SEARCHPDDGOODS, body);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
//                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().loadFinish();
                }
                LogUtil.e("拼多多搜索：" + result);
                if (page == 1) {
                    pddList.clear();
                }
                try {

                    if (result != null) {
                        SecondaryPddRecBean secondaryPddRecBean = JSON.parseObject(result, new TypeReference<SecondaryPddRecBean>() {
                        }.getType());
                        pddList.addAll(secondaryPddRecBean.getGoods_search_response().getGoods_list());
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }

                try {

                    if (pddWaterAdapter == null) {
                        pddWaterAdapter = new PddWaterAdapter(mContext, pddList, R.layout.item_classification_rec_grid);
                        pddLstAdapter = new SecondaryPddRecAdapter(mContext, pddList, R.layout.item_base_rec);
                        if (isWaterfall) {
                            if (getView() != null) {
                                getView().loadPDDWaterfallRv(pddWaterAdapter);
                            }
                        } else {
                            if (getView() != null) {
                                getView().loadPDDLstRv(pddLstAdapter);
                            }
                        }
                    } else {
                        if (page == 1) {
                            if (isWaterfall) {
                                if (getView() != null) {
                                    getView().loadPDDWaterfallRv(pddWaterAdapter);
                                }
                            } else {
                                if (getView() != null) {
                                    getView().loadPDDLstRv(pddLstAdapter);
                                }
                            }
                        } else {
                            if (isWaterfall) {
                                pddWaterAdapter.notifyItemChanged(10);
                            } else {
                                pddLstAdapter.notifyItemChanged(10);
                            }
                        }
                    }


                    if (pddLstAdapter != null) {
                        pddLstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                ARouter.getInstance().build("/module_classify/CommodityDetailsActivity")
                                        .withString("goods_id", pddList.get(position).getGoods_id() + "")
                                        .withString("type", "1")
                                        .navigation();
                            }
                        });
                    }

                    if (pddWaterAdapter != null) {
                        pddWaterAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                ARouter.getInstance().build("/module_classify/CommodityDetailsActivity")
                                        .withString("goods_id", pddList.get(position).getGoods_id() + "")
                                        .withString("type", "1")
                                        .navigation();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
//                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().loadFinish();
                }
            }
        }));
    }

    public void ChangeShow(int position) {
        switch (position) {
            case 0:
                if (isWaterfall) {
                    getView().loadTBLstRv(lstAdapter);
                    isWaterfall = false;
                } else {
                    getView().loadTBWaterfallRv(waterfallAdapter);
                    isWaterfall = true;
                }
                break;
            case 1:
                if (isWaterfall) {
                    getView().loadPDDLstRv(pddLstAdapter);
                    isWaterfall = false;
                } else {
                    getView().loadPDDWaterfallRv(pddWaterAdapter);
                    isWaterfall = true;
                }
                break;
            case 2:
                if (isWaterfall) {
                    getView().loadJDLstRv(jdLstAdapter);
                    isWaterfall = false;
                } else {
                    getView().loadJDWaterfallRv(jdWaterfallAdapter);
                    isWaterfall = true;
                }
                break;
        }

    }

    public void changeType(int index) {
        isSynthesize = index == 0 ? true : false;

        isPositiveSalesVolume = index == 1 ? !isPositiveSalesVolume : false;
        isSalesVolumeReduce = index == 1 ? !isSalesVolumeReduce : false;
        saleVolumTemp = index == 1 ? true : false;

        isPositivePrice = index == 2 ? !isPositivePrice : false;
        isPriceReduce = index == 2 ? !isPriceReduce : true;
        priceTemp = index == 2 ? true : false;

        isPositiveCredit = index == 3 ? !isPositiveCredit : false;
        isCreditReduce = index == 3 ? !isCreditReduce : false;
        creditTemp = index == 3 ? true : false;
        refreshData(1);
        getView().updateTitle(isPositiveSalesVolume, isPositivePrice, isPositiveCredit);
    }

    private void refreshData(int page) {
        if (saleVolumTemp) {
            synthesizeTemp = false;
            if (isSalesVolumeReduce) {
                if (goodsType == 1) {
                    searchTB(page, "total_sales_des");
                } else if (goodsType == 2) {
                    sort_type = 6;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, "desc", "inOrderCount30Days");
                }
            } else {
                if (goodsType == 1) {
                    searchTB(page, "total_sales_asc");
                } else if (goodsType == 2) {
                    sort_type = 5;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, "asc", "inOrderCount30Days");
                }
            }
        } else if (priceTemp) {
            synthesizeTemp = false;
            if (isPriceReduce) {
                if (goodsType == 1) {
                    searchTB(page, "price_des");
                } else if (goodsType == 2) {
                    sort_type = 4;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, "desc", "price");
                }
            } else {
                if (goodsType == 1) {
                    searchTB(page, "price_asc");
                } else if (goodsType == 2) {
                    sort_type = 3;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, "asc", "price");
                }
            }
        } else if (creditTemp) {
            synthesizeTemp = false;
            if (isCreditReduce) {
                if (goodsType == 1) {
                    searchTB(page, null);
                } else if (goodsType == 2) {
                    sort_type = 0;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, null, null);
                }
            } else {
                if (goodsType == 1) {
                    searchTB(page, null);
                } else if (goodsType == 2) {
                    sort_type = 0;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, null, null);
                }
            }
        } else if (isSynthesize) {
            if (!synthesizeTemp) {
                if (goodsType == 1) {
                    searchTB(page, null);
                } else if (goodsType == 2) {
                    sort_type = 0;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, null, null);
                }
                synthesizeTemp = true;
            }
        }
    }
}

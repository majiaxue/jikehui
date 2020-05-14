package com.example.user_mine;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bean.ApplicationBean;
import com.example.bean.BrowsingBean;
import com.example.bean.GoodsCollectCountBean;
import com.example.bean.MineOrderCountBean;
import com.example.bean.ShopCollectCountBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class MinePresenter extends BasePresenter<MineView> {

    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count6 = 0;
    private int size = 0;

    public MinePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void goodsCollectionCount() {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.GOODSCOLLECTION, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("setGoodsCollectionRec----->" + result);

                GoodsCollectCountBean goodsCollectionRecBean = JSON.parseObject(result, new TypeReference<GoodsCollectCountBean>() {
                }.getType());
                LogUtil.e("goodsCollectionRecBean----->" + goodsCollectionRecBean);
                if (goodsCollectionRecBean != null) {
                    if (goodsCollectionRecBean.getRecords() != null || goodsCollectionRecBean.getRecords().size() != 0) {
                        if (getView() != null) {
                            getView().goodsCollectionCount(goodsCollectionRecBean.getRecords().size());
                        }
                    } else {
                        if (getView() != null) {
                            getView().goodsCollectionCount(0);
                        }
                    }

                } else {
                    if (getView() != null) {
                        getView().goodsCollectionCount(0);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("setGoodsCollectionRecError------->" + errorCode);
                getView().goodsCollectionCount(0);

            }
        }));


    }

    public void shopCollectCount() {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.SELLERPAGE, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(final String result, String msg) {
                LogUtil.e("result--------->" + result);
                if (result != null) {
                    ShopCollectCountBean shopCollectCountBean = JSON.parseObject(result, new TypeReference<ShopCollectCountBean>() {
                    }.getType());
                    LogUtil.e("shopCollectList--------->" + shopCollectCountBean);
                    if (shopCollectCountBean != null) {
                        if (shopCollectCountBean.getRecords() != null || shopCollectCountBean.getRecords().size() != 0) {
                            if (getView() != null) {
                                getView().shopCollectCount(shopCollectCountBean.getRecords().size());
                            }
                        } else {
                            if (getView() != null) {
                                getView().shopCollectCount(0);
                            }
                        }
                    } else {
                        if (getView() != null) {
                            getView().shopCollectCount(0);
                        }
                    }
                } else {
                    if (getView() != null) {
                        getView().shopCollectCount(0);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("errorMSG----->" + errorMsg);
                getView().shopCollectCount(0);

            }
        }));
    }

    public void browsingHistoryCount() {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.HISTORYALL, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("browsingHistoryRecResult------------->" + result);
                LogUtil.e("browsingHistoryRecMsg------------->" + msg);
                BrowsingBean browsingBean = JSON.parseObject(result, new TypeReference<BrowsingBean>() {
                }.getType());
                if (browsingBean != null) {
                    if (getView() != null) {
                        for (int i = 0; i < browsingBean.getRecords().size(); i++) {
                            size += browsingBean.getRecords().get(i).getItem().size();
                        }
                        getView().browsingHistoryCount(size);
                        size = 0;
                    }
                } else {
                    getView().browsingHistoryCount(0);
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("browsingHistoryRecErrorMsg------------->" + errorMsg);
                getView().browsingHistoryCount(0);

            }
        }));

    }

    //查询商家申请
    public void businessApplication() {
        Map build = MapUtil.getInstance().addParms("userCode", SPUtil.getUserCode()).build();
        Observable<ResponseBody> data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.SELLERSTATE, build);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("mineFragmentResult--------------->" + result);
                ApplicationBean applicationBean = JSON.parseObject(result, new TypeReference<ApplicationBean>() {
                }.getType());
                if (applicationBean != null) {
                    String data1 = applicationBean.getData();
                    LogUtil.e("mineFragment" + data1);
                    if (data1.equals("2") || data1.equals("3")) {
                        ARouter.getInstance().build("/module_user_mine/BusinessApplicationActivity").withString(CommonResource.HISTORY_LOCAL, "shop").navigation();
                    } else {
                        Toast.makeText(mContext, "您已经是商家了无需申请!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("mineFragmentErrorMsg--------------->" + errorMsg);
            }
        }));
    }

    //我的订单
    public void mineOrderAll() {

        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.ORDERALL, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("OrderAllPresenterResult-------->" + result);

                MineOrderCountBean mineOrderBean = new Gson().fromJson(result, MineOrderCountBean.class);
                if (mineOrderBean != null && mineOrderBean.getOrderList().size() != 0) {
                    for (int i = 0; i < mineOrderBean.getOrderList().size(); i++) {

//                        if (mineOrderBean.getOrderList().get(i).getBackStatus() == -1) {
                        if (mineOrderBean.getOrderList().get(i).getStatus() == 2) {
                            //2待收货
                            count2++;
                        }
                        if (mineOrderBean.getOrderList().get(i).getStatus() == 6) {
                            //6待付款
                            count6++;
                        }
                        if (mineOrderBean.getOrderList().get(i).getStatus() == 3) {
                            //3待评论
                            count3++;
                        }
                        if (mineOrderBean.getOrderList().get(i).getStatus() == 1) {
                            //1待发货
                            count1++;
                        }
//                        }
                    }
                    getView().daishouhuo(count2);
                    count2 = 0;
                    getView().daifukuan(count6);
                    count6 = 0;
                    getView().daipingjia(count3);
                    count3 = 0;
                    getView().daifahuo(count1);
                    count1 = 0;
                } else {
                    getView().daishouhuo(0);
                    getView().daifukuan(0);
                    getView().daipingjia(0);
                    getView().daifahuo(0);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("OrderAllPresenterError-------->" + errorMsg);
                getView().daishouhuo(0);
                getView().daifukuan(0);
                getView().daipingjia(0);
                getView().daifahuo(0);
            }
        }));
    }
}

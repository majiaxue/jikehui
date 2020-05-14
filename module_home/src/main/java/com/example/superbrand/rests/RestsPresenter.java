package com.example.superbrand.rests;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.trade.biz.applink.adapter.AlibcFailModeType;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.RestsBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.superbrand.adapter.RestsAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class RestsPresenter extends BasePresenter<RestsView> {

    private List<RestsBean.DataBeanX> dataListBean = new ArrayList<>();
    private RestsAdapter restsAdapter;

    public RestsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initList(final int page, int index) {
        LogUtil.e("index          "+index+"page        "+page);
        Map build = MapUtil.getInstance().addParms("min_id", page).addParms("brandcat", index).build();
        final Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.BRANDLIST, build);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("RestsPresenter" + result);
                if (result.contains("\"code\":1")) {
                    final RestsBean restsBean = JSON.parseObject(result, new TypeReference<RestsBean>() {
                    }.getType());

                    if (page == 1) {
                        dataListBean.clear();
                    }
                    dataListBean.addAll(restsBean.getData());

                    for (int i = 0; i < dataListBean.size(); i++) {
                        if (dataListBean.get(i).getItem() == null) {
                            dataListBean.remove(i);
                        }
                    }
                    if (restsAdapter == null) {
                        restsAdapter = new RestsAdapter(mContext, dataListBean, R.layout.item_rests_rec);
                        if (getView() != null) {
                            getView().loadAdapter(restsAdapter);
                        }
                    } else {
                        restsAdapter.notifyDataSetChanged();
                    }

                    restsAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int index) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
                                    //提供给三方传递配置参数
                                    Map<String, String> trackParams = new HashMap<>();
                                    //设置页面打开方式
                                    AlibcShowParams showParams = new AlibcShowParams();
                                    showParams.setOpenType(OpenType.Auto);
                                    showParams.setClientType("taobao");
                                    showParams.setBackUrl("");
                                    showParams.setNativeOpenFailedMode(AlibcFailModeType.AlibcNativeFailModeJumpH5);

                                    //使用百川sdk提供默认的Activity打开detail
                                    AlibcTrade.openByUrl((Activity) mContext, "", restsBean.getData().get(index).getItem().get(0).getCouponurl(), null, new WebViewClient(), new WebChromeClient(), showParams, taokeParams, trackParams, new AlibcTradeCallback() {
                                        @Override
                                        public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {
                                            //打开电商组件，用户操作中成功信息回调。tradeResult：成功信息（结果类型：加购，支付；支付结果）
                                            LogUtil.e(alibcTradeResult.toString());
                                        }

                                        @Override
                                        public void onFailure(int i, String s) {
                                            //打开电商组件，用户操作中错误信息回调。i：错误码；s：错误信息
                                            LogUtil.e("阿里百川" + i + "         " + s);
                                        }
                                    });
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(mContext, "没有更多商品了", Toast.LENGTH_SHORT).show();
                }

                if (getView() != null) {
                    getView().refreshSuccess();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("RestsPresenterErrorMsg" + errorMsg);
                if (getView() != null) {
                    getView().refreshSuccess();
                }
            }
        }));
    }

}

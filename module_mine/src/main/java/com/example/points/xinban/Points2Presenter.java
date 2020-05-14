package com.example.points.xinban;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.points.xinban.adapter.Points2Adapter;
import com.example.bean.MingXiBean;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import io.reactivex.Observable;

public class Points2Presenter  extends BasePresenter<Points2View> {
    private MingXiBean recordBeans;
    private Points2Adapter adapter;
    public Points2Presenter(Context context) {
        super(context);
    }
    //积分明细列表
    public void loadData() {
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.JIFENMINGXI, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("新的积分明细：" + result);
                try {
                    if (result != null) {
                        recordBeans = JSON.parseObject(result, MingXiBean.class);
                        adapter = new Points2Adapter(mContext, recordBeans.getIntegralHistoryList(), R.layout.item_jifen_list);
                        if (getView() != null) {
                            getView().loadRv(adapter);
                            getView().loadData(recordBeans);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-------" + errorMsg);
            }
        }));
    }

    @Override
    protected void onViewDestroy() {

    }
}

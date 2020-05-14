package com.example.balance.income;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.balance.adapter.IncomeAdapter;
import com.example.bean.InAndOutBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class IncomePresenter extends BasePresenter<IncomeView> {
    private List<InAndOutBean.RecordsBean> dataList = new ArrayList<>();
    private IncomeAdapter adapter;

    public IncomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page) {
        Map map = MapUtil.getInstance().addParms("type", "0").addParms("current", page).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.IN_OUT, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("收入：" + result);
                InAndOutBean inAndOutBean = JSON.parseObject(result, InAndOutBean.class);
                if (page == 1) {
                    dataList.clear();
                }
                dataList.addAll(inAndOutBean.getRecords());
                if (adapter == null) {
                    adapter = new IncomeAdapter(mContext, dataList, R.layout.rv_income);
                    if (getView() != null) {
                        getView().loadRv(adapter);
                    }
                } else {
                    adapter.notifyDataSetChanged();
                }
                if (getView() != null) {
                    getView().loadFinish();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "----shouru----" + errorMsg);
                if (getView() != null) {
                    getView().loadFinish();
                }
            }
        }));
    }
}

package com.example.hehuorenfans;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.bean.PartnerBean;
import com.example.bean.PartnerListBean;
import com.example.common.CommonResource;
import com.example.hehuorenfans.adapter.PartnerAdapter;
import com.example.module_home.R;
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
import okhttp3.ResponseBody;

public class PartnerPresenter extends BasePresenter<PartnerView> {

    private List<PartnerListBean> datalist=new ArrayList<>();
    private PartnerAdapter adapter;

    public PartnerPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getData(String type1){
        //     127.0.0.1:4001/rest/user/countPartnerNumAndShopNumByUserCode    get请求 参数token type 0 合伙人 1 门店
        Map type = MapUtil.getInstance().addParms("type", type1).build();

        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.HEHUORENNUMBER,type, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("合伙人信息----------"+result);
                PartnerBean partnerBean = JSON.parseObject(result, PartnerBean.class);
                getView().loadPartner(partnerBean);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("合伙人信息----------"+errorCode+errorMsg);
            }
        }));
    }
    public void getDtaList(final int page, String type2){
//        下面列表接口 127.0.0.1:4001/rest/user/getPartnerNumAndShopNumByUserCode
        Map type = MapUtil.getInstance().addParms("type", "0").addParms("page",type2).build();
        Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.HEHUORENNUMBERLIST, type, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(head,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("list-------"+result);
                List<PartnerListBean> partnerListBeans = JSON.parseArray(result, PartnerListBean.class);
                if (page==1){
                    datalist.clear();
                }
                datalist.addAll(partnerListBeans);
                if (adapter==null){
                    adapter=new PartnerAdapter(mContext,datalist, R.layout.item_partner);
                    getView().loadAdapter(adapter);
                    getView().loadFinish();
                }else {
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}

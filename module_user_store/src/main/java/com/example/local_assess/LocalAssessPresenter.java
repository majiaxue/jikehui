package com.example.local_assess;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.bean.AssessBean;
import com.example.bean.LocalOrderBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class LocalAssessPresenter extends BasePresenter<LocalAssessView> {
    public LocalAssessPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void commit(int totalStarCount, int serveStarCount, int descriptionStarCount, String edit, LocalOrderBean bean) {
        if (totalStarCount == 0 || serveStarCount == 0 || descriptionStarCount == 0) {
            Toast.makeText(mContext, "请先打分", Toast.LENGTH_SHORT).show();
        } else {
            AssessBean.RecordsBean assessBean = new AssessBean.RecordsBean();
            assessBean.setNickname(SPUtil.getStringValue(CommonResource.USER_NAME));
            assessBean.setIcon(SPUtil.getStringValue(CommonResource.USER_PIC));
            assessBean.setPjpf(totalStarCount);
            assessBean.setFwpf(serveStarCount);
            assessBean.setSppf(descriptionStarCount);
            assessBean.setInfo(edit);
//            assessBean.setSellerId(Integer.valueOf(bean.getLocalSellerId()));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bean.getLocalOrderItemList().size(); i++) {
                if (i == bean.getLocalOrderItemList().size() - 1) {
                    sb.append(bean.getLocalOrderItemList().get(i).getGoodsName() + "-" + bean.getLocalOrderItemList().get(i).getGoodsSpec());
                } else {
                    sb.append(bean.getLocalOrderItemList().get(i).getGoodsName() + "-" + bean.getLocalOrderItemList().get(i).getGoodsSpec() + ",");
                }
            }
            assessBean.setAttr(sb.toString());
            String jsonString = JSON.toJSONString(assessBean);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
            Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postDataWithBody(CommonResource.GETASSESS, requestBody);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("评价：" + result);
                    Toast.makeText(mContext, "评价成功", Toast.LENGTH_SHORT).show();
                    ((Activity) mContext).finish();
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e(errorCode + "----------" + errorMsg);
                }
            }));
        }
    }
}

package com.example.local_store.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalCartBean;
import com.example.bean.LocalStoreBean;
import com.example.common.CommonResource;
import com.example.module_local.R;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class LocalStoreCommendAdapter extends MyRecyclerAdapter<LocalStoreBean.ListBean> {
    public LocalStoreCommendAdapter(Context context, List<LocalStoreBean.ListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(final RecyclerViewHolder holder, final LocalStoreBean.ListBean data, int position) {
        if (data.isTitle()) {
            holder.getView(R.id.rv_local_store_commend_parent).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.rv_local_store_commend_parent).setVisibility(View.VISIBLE);
            holder.setText(R.id.rv_local_store_commend_name, data.getName())
                    .setImageUrl(R.id.rv_local_store_commend_img, data.getPics())
                    .setText(R.id.rv_local_store_commend_yueshou, data.getMonthSales());
            if (TextUtils.isEmpty(data.getDiscountPrice()) || "0".equals(data.getDiscountPrice())) {
                holder.setText(R.id.rv_local_store_commend_price, data.getPrice());
            } else {
                holder.setText(R.id.rv_local_store_commend_price, data.getDiscountPrice());
            }

            holder.getView(R.id.rv_local_store_commend_add).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addGoods(data);

                    int currentCount = data.getCount();
                    if (currentCount == 0) {
                        holder.getView(R.id.rv_local_store_commend_minus).setAnimation(getShowAnimation());
                        holder.getView(R.id.rv_local_store_commend_count).setAnimation(getShowAnimation());
                    }
                    currentCount++;
                    holder.getView(R.id.rv_local_store_commend_minus).setEnabled(true);
                    holder.setText(R.id.rv_local_store_commend_count, currentCount + "");
                    data.setCount(currentCount);
                    isShow(data, holder.getView(R.id.rv_local_store_commend_count), holder.getView(R.id.rv_local_store_commend_minus));
                    notifyDataSetChanged();
                }
            });

            holder.getView(R.id.rv_local_store_commend_minus).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    minusGoods(data);
                    v.setEnabled(false);

                    Integer currentCount = data.getCount();
                    currentCount--;
                    if (currentCount <= 0) {
                        currentCount = 0;
                        holder.getView(R.id.rv_local_store_commend_minus).setEnabled(false);
                        holder.getView(R.id.rv_local_store_commend_minus).setAnimation(getHiddenAnimation());
                        holder.getView(R.id.rv_local_store_commend_count).setAnimation(getHiddenAnimation());
                    }
                    holder.setText(R.id.rv_local_store_commend_count, currentCount + "");
                    data.setCount(currentCount);
                    isShow(data, holder.getView(R.id.rv_local_store_commend_count), holder.getView(R.id.rv_local_store_commend_minus));
                    notifyDataSetChanged();
                }
            });

//            if (viewThreeOnClickListener != null) {
//                viewThreeOnClickListener.ViewThreeOnClick(holder.getView(R.id.rv_local_store_commend_minus), holder.getView(R.id.rv_local_store_commend_count), holder.getView(R.id.rv_local_store_commend_add), position);
//            }
        }
    }

    private void isShow(LocalStoreBean.ListBean commodity, View view1, View view2) {
        if (commodity.getCount() > 0) {
            view2.setVisibility(View.VISIBLE);
            view1.setVisibility(View.VISIBLE);
        } else {
            view2.setVisibility(View.GONE);
            view1.setVisibility(View.GONE);
        }
    }

    private void addGoods(LocalStoreBean.ListBean data) {
        LocalCartBean.InsideCart goodsToCartBean = new LocalCartBean.InsideCart(SPUtil.getStringValue(CommonResource.SELLERID), data.getId(), SPUtil.getUserCode(), data.getCount());
        String jsonString = JSON.toJSONString(goodsToCartBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_CART_ADD, requestBody);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("添加商品：" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

    private void minusGoods(LocalStoreBean.ListBean data) {
        LocalCartBean.InsideCart goodsToCartBean = new LocalCartBean.InsideCart(SPUtil.getStringValue(CommonResource.SELLERID), data.getId(), SPUtil.getUserCode(), data.getCount());
        String jsonString = JSON.toJSONString(goodsToCartBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody(CommonResource.LOCAL_CART_MINUS, requestBody);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("去掉商品：" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }


    //显示减号的动画
    private Animation getShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 1f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    //隐藏减号的动画
    private Animation getHiddenAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 1f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
}

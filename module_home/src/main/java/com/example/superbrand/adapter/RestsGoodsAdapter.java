package com.example.superbrand.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.RestsBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class RestsGoodsAdapter extends MyRecyclerAdapter<RestsBean.DataBeanX.ItemBean> {

    public RestsGoodsAdapter(Context context, List<RestsBean.DataBeanX.ItemBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RestsBean.DataBeanX.ItemBean data, int position) {
        holder.setImageFresco(R.id.rests_goods_image, data.getItempic());
        holder.setText(R.id.rests_goods_price, "￥" + data.getItemprice());
        double price = Double.valueOf(data.getItemprice()) - Double.valueOf(data.getCouponmoney());
        double rate = Double.valueOf(data.getTkrates()) / 100;
        double mul = price * rate * 0.9;
        holder.setText(R.id.rests_goods_estimate, "预估赚" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
        LogUtil.e("信息" + "优惠价" + price + "佣金" + rate + "个人佣金" + SPUtil.getFloatValue(CommonResource.BACKBL));
    }
}

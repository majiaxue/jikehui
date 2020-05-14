package com.example.type_detail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.HotSaleBean;
import com.example.common.CommonResource;
import com.example.user_store.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class TypeDetailLstAdapter extends MyRecyclerAdapter<HotSaleBean.DataBean> {
    public TypeDetailLstAdapter(Context context, List<HotSaleBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, HotSaleBean.DataBean data, int position) {
        float value = SPUtil.getFloatValue(CommonResource.BACKBL);
        double predict = ArithUtil.mul(ArithUtil.mul(value, data.getReturnRatio() / 100), data.getPrice());

        holder.setImageFresco(R.id.type_detail_lst_image, data.getPic())
                .setText(R.id.type_detail_lst_name, data.getName())
                .setText(R.id.type_detail_lst_price, "￥" + data.getPrice())
                .setText(R.id.type_detail_lst_predict, "预估赚￥" + predict)
                .setText(R.id.type_detail_lst_payment_amount, data.getSale() + "人付款")
                .setText(R.id.type_detail_lst_good_reputation, "尚无评论".equals(data.getGoodReputation()) ? "尚无评论" : data.getGoodReputation() + "好评")
                .setText(R.id.type_detail_lst_shop_name, data.getSellerName());
        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.type_detail_lst_btn), position);
        }
    }
}

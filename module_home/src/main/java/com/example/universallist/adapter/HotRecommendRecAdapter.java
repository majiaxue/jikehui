package com.example.universallist.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.HotRecommendBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.utils.ArithUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class HotRecommendRecAdapter extends MyRecyclerAdapter<HotRecommendBean.DataBean> {

    public HotRecommendRecAdapter(Context context, List<HotRecommendBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, HotRecommendBean.DataBean data, int position) {
        double commissionRate = Double.valueOf(data.getTkrates()) / 100;
        double mul = commissionRate * (Double.valueOf(data.getItemprice()) - Double.valueOf(data.getCouponmoney())) * 0.9;
        holder.setImageFresco(R.id.universal_list_rec_image, data.getItempic());
        holder.setText(R.id.universal_list_rec_name, data.getItemtitle());
        holder.setText(R.id.universal_list_rec_price, "￥" + data.getItemprice());
        holder.setText(R.id.universal_list_rec_payment_amount, "可用购物金" + data.getCouponmoney() + "元");
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            if (SPUtil.getFloatValue(CommonResource.BACKBL) != 0) {
                holder.setText(R.id.universal_list_rec_yuguzhuan, "预估赚" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
            } else {
                holder.setText(R.id.universal_list_rec_yuguzhuan, "预估赚" + ArithUtil.mulRound(mul, 0.3));
            }
        } else {
            holder.setText(R.id.universal_list_rec_yuguzhuan, "预估赚" + ArithUtil.mulRound(mul, 0.3));
        }
        holder.setText(R.id.universal_list_rec_shengjizhuan, "升级赚" + ArithUtil.mulRound(mul, 0.8));
    }
}

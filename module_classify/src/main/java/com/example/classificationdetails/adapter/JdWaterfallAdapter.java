package com.example.classificationdetails.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.JDListBean;
import com.example.module_classify.R;
import com.example.utils.ArithUtil;

import java.util.List;

public class JdWaterfallAdapter extends MyRecyclerAdapter<JDListBean.DataBean> {
    public JdWaterfallAdapter(Context context, List<JDListBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, JDListBean.DataBean data, int position) {
        holder.setImageFresco(R.id.classification_image, data.getImageInfo().getImageList().get(0).getUrl());
        holder.setText(R.id.classification_name, data.getSkuName());
        if (data.getCouponInfo() != null && data.getCouponInfo().getCouponList() != null && data.getCouponInfo().getCouponList().size() > 0) {
            holder.setText(R.id.classification_reduce_price, "领劵减" + Double.valueOf(data.getCouponInfo().getCouponList().get(0).getDiscount()) + "元");
        } else {
            holder.setText(R.id.classification_reduce_price, "暂无优惠劵");
        }
        if (data.getCouponInfo().getCouponList().size() != 0) {
            holder.setText(R.id.classification_preferential_price, "￥" + ArithUtil.sub(Double.valueOf(data.getPriceInfo().getPrice()), Double.valueOf(data.getCouponInfo().getCouponList().get(0).getDiscount())));
        } else {
            holder.setText(R.id.classification_preferential_price, "￥" + Double.valueOf(data.getPriceInfo().getPrice()));
        }
        holder.setText(R.id.classification_original_price, "" + Double.valueOf(data.getPriceInfo().getPrice()) + "");
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        TextView originalPrice = holder.getView(R.id.classification_original_price);
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }
}

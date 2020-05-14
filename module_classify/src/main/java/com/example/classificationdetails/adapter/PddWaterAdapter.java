package com.example.classificationdetails.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.SecondaryPddRecBean;
import com.example.module_classify.R;
import com.example.utils.ArithUtil;

import java.util.List;

public class PddWaterAdapter extends MyRecyclerAdapter<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> {
    public PddWaterAdapter(Context context, List<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean data, int position) {
        holder.setImageFresco(R.id.classification_image, data.getGoods_thumbnail_url());
        holder.setText(R.id.classification_name, data.getGoods_name());
        holder.setText(R.id.classification_reduce_price, "领劵减" + ArithUtil.div(Double.valueOf(data.getCoupon_discount()), 100, 1) + "元");
        holder.setText(R.id.classification_preferential_price, "￥" + ArithUtil.div(Double.valueOf(data.getMin_group_price()) - Double.valueOf(data.getCoupon_discount()), 100, 1));
        holder.setText(R.id.classification_original_price, "" + ArithUtil.div(Double.valueOf(data.getMin_group_price()), 100, 1));
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        TextView originalPrice = holder.getView(R.id.classification_original_price);
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }
}

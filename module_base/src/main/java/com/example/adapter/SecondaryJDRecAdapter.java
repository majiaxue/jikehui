package com.example.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.bean.JDGoodsRecBean;

import com.example.bean.JDListBean;
import com.example.common.CommonResource;
import com.example.module_base.R;
import com.example.utils.ArithUtil;
import com.example.utils.SPUtil;

import java.util.List;


/**
 * Created by cuihaohao on 2019/6/6
 * Describe:
 */
public class SecondaryJDRecAdapter extends MyRecyclerAdapter<JDListBean.DataBean> {


    public SecondaryJDRecAdapter(Context context, List<JDListBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, JDListBean.DataBean data, int position) {
        holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.jingdong);

        double div;
        if (data.getCouponInfo().getCouponList().size() == 0) {
            div = data.getPriceInfo().getPrice();
        } else {
            div = ArithUtil.sub(Double.valueOf(data.getPriceInfo().getPrice()), Double.valueOf(data.getCouponInfo().getCouponList().get(0).getDiscount()));
        }
        holder.setImageFresco(com.example.module_base.R.id.base_image, data.getImageInfo().getImageList().get(0).getUrl());
        holder.setText(com.example.module_base.R.id.base_name, data.getSkuName());
        if (data.getCouponInfo().getCouponList().size() > 0) {
            holder.setText(R.id.base_reduce_price, "可用购物金" + Double.valueOf(data.getCouponInfo().getCouponList().get(0).getDiscount()) + "元");
        } else {
            holder.setText(R.id.base_reduce_price, "暂无优惠");
        }
        holder.setText(com.example.module_base.R.id.base_preferential_price, "￥" + div);
        holder.setText(com.example.module_base.R.id.base_original_price, Double.valueOf(data.getPriceInfo().getPrice()) + "");
        holder.setText(com.example.module_base.R.id.base_number, "已抢" + data.getInOrderCount30Days() + "件");
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mul(Double.valueOf(data.getCommissionInfo().getCommission()), SPUtil.getFloatValue(CommonResource.BACKBL))*0.6);
        } else {
            holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mul(Double.valueOf(data.getCommissionInfo().getCommission()), 0.6));
        }
        holder.setText(R.id.base_upgrade, "升级赚" + ArithUtil.mul(Double.valueOf(data.getCommissionInfo().getCommission()), 0.8));
//        TextView immediatelyGrab = holder.getView(R.id.base_immediately_grab);
//        if (viewOnClickListener != null) {
//            viewOnClickListener.ViewOnClick(immediatelyGrab, position);
//        }
    }
}

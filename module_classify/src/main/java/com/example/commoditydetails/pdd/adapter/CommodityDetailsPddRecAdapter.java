package com.example.commoditydetails.pdd.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.CommodityDetailsPddRecBean;
import com.example.common.CommonResource;
import com.example.module_classify.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/11
 * Describe:
 */
public class CommodityDetailsPddRecAdapter extends MyRecyclerAdapter<CommodityDetailsPddRecBean.TopGoodsListGetResponseBean.ListBean> {

    public CommodityDetailsPddRecAdapter(Context context, List<CommodityDetailsPddRecBean.TopGoodsListGetResponseBean.ListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, CommodityDetailsPddRecBean.TopGoodsListGetResponseBean.ListBean data, int position) {
        //拼多多
        holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.pinduoduo);
        double div = ArithUtil.div(data.getMin_group_price() - data.getCoupon_discount(), 100, 2);
        double mul = ArithUtil.mul(div, ArithUtil.div(data.getPromotion_rate(), 1000, 2));
        holder.setImageFresco(com.example.module_base.R.id.base_image, data.getGoods_thumbnail_url());
        holder.setText(com.example.module_base.R.id.base_name, data.getGoods_name());
        holder.setText(R.id.base_reduce_price, "可用购物金" + ArithUtil.div(data.getCoupon_discount(), 100, 2) + "元");
        holder.setText(R.id.base_preferential_price, "￥" + div);
        holder.setText(R.id.base_original_price, "" + ArithUtil.div(data.getMin_group_price(),100,2));
        holder.setText(R.id.base_number, "已抢" + data.getSold_quantity());
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            if (SPUtil.getFloatValue(CommonResource.BACKBL) != 0) {
                holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
            } else {
                holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mulRound(mul, 0.3));
            }
//            LogUtil.e("预估收益:" + "商品价格" + couponPrice + "佣金" + div + "个人收益" + SPUtil.getFloatValue(CommonResource.BACKBL) + "最终金额" + "预估赚" + ArithUtil.mul(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
        } else {
            holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mulRound(mul, 0.3));
        }
        holder.setText(com.example.module_base.R.id.base_upgrade, "升级赚"+ArithUtil.mul(mul,0.8));
//        TextView immediatelyGrab = holder.getView(com.example.module_base.R.id.base_immediately_grab);
//        viewOnClickListener.ViewOnClick(immediatelyGrab, position);
    }
}

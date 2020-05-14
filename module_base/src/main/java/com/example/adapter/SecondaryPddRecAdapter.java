package com.example.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bean.SecondaryPddRecBean;
import com.example.common.CommonResource;
import com.example.module_base.R;
import com.example.utils.ArithUtil;
import com.example.utils.GlideRoundTransform;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;


/**
 * Created by cuihaohao on 2019/6/6
 * Describe:
 */
public class SecondaryPddRecAdapter extends MyRecyclerAdapter<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> {

    public SecondaryPddRecAdapter(Context context, List<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(final RecyclerViewHolder holder, SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean data, int position) {

        //拼多多
        holder.setImageResource(R.id.base_type, R.drawable.pinduoduo);

        double div = ArithUtil.div(Double.valueOf(data.getMin_group_price()) - Double.valueOf(data.getCoupon_discount()), 100, 2);
        double mul = ArithUtil.mul(div, ArithUtil.div(Double.valueOf(data.getPromotion_rate()), 1000, 2));

        holder.setImageFresco(com.example.module_base.R.id.base_image, data.getGoods_thumbnail_url());

//        Glide.with(context)
////                .asBitmap()
//                .load(data.getGoods_thumbnail_url())
//                .transform(new GlideRoundTransform(5,0))
//                .into((ImageView) holder.getView(R.id.base_image));

        holder.setText(R.id.base_name, data.getGoods_name());
        holder.setText(R.id.base_reduce_price, "可用购物金" + ArithUtil.div(Double.valueOf(data.getCoupon_discount()), 100, 2) + "元");
        holder.setText(R.id.base_preferential_price, "￥" + div);
        holder.setText(R.id.base_original_price, "" + ArithUtil.div(Double.valueOf(data.getMin_group_price()), 100, 2));
        if (!TextUtils.isEmpty(data.getSales_tip())){
            holder.setText(R.id.base_number, "已抢" + data.getSales_tip());
        }else{
            holder.setText(R.id.base_number, "已抢" + 0+"件");
        }
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            if (SPUtil.getFloatValue(CommonResource.BACKBL) != 0) {
                holder.setText(R.id.base_estimate, "预估赚" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL))*0.6);
            } else {
                holder.setText(R.id.base_estimate, "预估赚" + ArithUtil.mulRound(mul, 0.6));
            }
//            LogUtil.e("预估收益:" + "商品价格" + couponPrice + "佣金" + div + "个人收益" + SPUtil.getFloatValue(CommonResource.BACKBL) + "最终金额" + "预估赚" + ArithUtil.mul(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
        } else {
            holder.setText(R.id.base_estimate, "预估赚" + ArithUtil.mulRound(mul, 0.6));
        }
        holder.setText(R.id.base_upgrade, "升级赚"+ArithUtil.mul(mul,0.8));

//        TextView immediatelyGrab = holder.getView(R.id.base_immediately_grab);
//        if (viewOnClickListener != null) {
//            viewOnClickListener.ViewOnClick(immediatelyGrab, position);
//        }
    }
}

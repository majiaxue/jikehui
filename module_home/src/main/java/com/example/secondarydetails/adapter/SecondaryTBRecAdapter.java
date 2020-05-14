package com.example.secondarydetails.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.bean.TBGoodsRecBean;
import com.example.utils.ArithUtil;
import com.example.utils.GlideRoundTransform;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;


/**
 * Created by cuihaohao on 2019/6/6
 * Describe:
 */
public class SecondaryTBRecAdapter extends MyRecyclerAdapter<TBGoodsRecBean.ResultListBean> {


    public SecondaryTBRecAdapter(Context context, List<TBGoodsRecBean.ResultListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(final RecyclerViewHolder holder, TBGoodsRecBean.ResultListBean data, int position) {
        // 1表示天猫，0表示淘宝产品
        if ("0".equals(data.getUser_type())) {
            //淘宝
            holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.taobao);
        } else {
            //天猫
            holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.tianmao);
        }

        double sub = Double.valueOf(data.getZk_final_price() == null ? "0.0" : data.getZk_final_price()) - Double.valueOf(data.getCoupon_amount() == null ? "0.0" : data.getCoupon_amount());
        double div = Double.valueOf(data.getCommission_rate()) / 10000;
        double mul = sub * div * 0.9;//商品收益需要乘个人收益
        holder.setImageFresco(com.example.module_base.R.id.base_image, data.getPict_url());

        holder.setText(com.example.module_base.R.id.base_name, data.getTitle());
        holder.setText(com.example.module_base.R.id.base_reduce_price, "可用购物金" + (data.getCoupon_amount() == null ? "0" : data.getCoupon_amount()) + "元");
        holder.setText(com.example.module_base.R.id.base_preferential_price, "￥" + ArithUtil.sub(Double.valueOf(data.getZk_final_price() == null ? "0.0" : data.getZk_final_price()), Double.valueOf(data.getCoupon_amount() == null ? "0.0" : data.getCoupon_amount())));
        holder.setText(com.example.module_base.R.id.base_original_price, "￥" + data.getZk_final_price());
        holder.setText(com.example.module_base.R.id.base_number, "已抢" + data.getVolume() + "件");
        LogUtil.e("百分比--------"+SPUtil.getFloatValue(CommonResource.BACKBL));
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            if (SPUtil.getFloatValue(CommonResource.BACKBL) != 0) {
                holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL))*0.6);
            } else {
                holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mulRound(mul, 0.6));
            }
//            LogUtil.e("预估收益：" + "个人收益" + SPUtil.getFloatValue(CommonResource.BACKBL) + "商品佣金" + div + "商品价格" + sub + "最终收益" + ArithUtil.mul(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
        } else {
            holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mulRound(mul, 0.6));
        }
        holder.setText(com.example.module_base.R.id.base_upgrade, "升级赚" + ArithUtil.mulRound(mul, 0.6));
//        TextView immediatelyGrab = holder.getView(R.id.base_immediately_grab);
//        viewOnClickListener.ViewOnClick(immediatelyGrab, position);
    }

}

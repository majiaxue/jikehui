package com.example.commoditydetails.taobao.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TBGoodChoiceBean;
import com.example.common.CommonResource;
import com.example.module_base.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/13
 * Describe:
 */
public class TBRecommendAdapter extends MyRecyclerAdapter<TBGoodChoiceBean.DataBean> {

    public TBRecommendAdapter(Context context, List<TBGoodChoiceBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TBGoodChoiceBean.DataBean data, int position) {
        // 1表示天猫，0表示淘宝产品
        if (data.getUser_type().equals("0")) {
            //淘宝
            holder.setImageResource(R.id.base_type, R.drawable.taobao);
        } else {
            //天猫
            holder.setImageResource(R.id.base_type, R.drawable.tianmao);
        }
        double sub = Double.valueOf(data.getZk_final_price()) - Double.valueOf(data.getCoupon_amount());
        double div = Double.valueOf(data.getCommission_rate()) / 100;
        double mul = sub * div * 0.9;//商品收益需要乘个人收益

        holder.setImageFresco(R.id.base_image, data.getPict_url());
        holder.setText(R.id.base_name, data.getTitle());
        holder.setText(R.id.base_reduce_price, "可用购物金" + data.getCoupon_amount() + "元");
        holder.setText(R.id.base_preferential_price, "￥" + ArithUtil.sub(Double.valueOf(data.getZk_final_price()) , Double.valueOf(data.getCoupon_amount())));
        holder.setText(R.id.base_original_price, data.getZk_final_price());
        holder.setText(R.id.base_number, "已抢" + data.getVolume() + "件");
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);
        holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
        holder.setText(com.example.module_base.R.id.base_upgrade, "升级赚" + ArithUtil.mulRound(mul, 0.8));
        LogUtil.e("这是预估啊-------------"+mul);
        LogUtil.e("这是预估啊-------------"+ ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
//        TextView immediatelyGrab = holder.getView(R.id.base_immediately_grab);
//        viewOnClickListener.ViewOnClick(immediatelyGrab, position);
    }
}

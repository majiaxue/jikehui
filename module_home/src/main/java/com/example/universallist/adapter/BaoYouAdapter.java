package com.example.universallist.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TBGoodsRecBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class BaoYouAdapter extends MyRecyclerAdapter<TBGoodsRecBean.ResultListBean> {

    public BaoYouAdapter(Context context, List<TBGoodsRecBean.ResultListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TBGoodsRecBean.ResultListBean data, int position) {
        double commissionRate = Double.valueOf(data.getCommission_rate() == null ? "0" : data.getCommission_rate()) / 10000;
        double mul = commissionRate * (Double.valueOf(data.getZk_final_price() == null ? "0" : data.getZk_final_price()) - Double.valueOf(data.getCoupon_amount() == null ? "0" : data.getCoupon_amount())) * 0.9;
        holder.setImageFresco(R.id.universal_list_rec_image, data.getPict_url());
        holder.setText(R.id.universal_list_rec_name, data.getTitle());
        holder.setText(R.id.universal_list_rec_price, "￥" + data.getZk_final_price());
        holder.setText(R.id.universal_list_rec_payment_amount, "领劵减" + data.getCoupon_amount() == null ? "0" : data.getCoupon_amount() + "元");
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            if (SPUtil.getFloatValue(CommonResource.BACKBL) != 0) {
                holder.setText(R.id.universal_list_rec_yuguzhuan, "预估赚" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));

            } else {
                holder.setText(R.id.universal_list_rec_yuguzhuan, "预估赚" + ArithUtil.mulRound(mul, 0.3));
            }
//            LogUtil.e("预估收益:" + "商品价格" + couponPrice + "佣金" + div + "个人收益" + SPUtil.getFloatValue(CommonResource.BACKBL) + "最终金额" + "预估赚" + ArithUtil.mul(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
        } else {
            holder.setText(R.id.universal_list_rec_yuguzhuan, "预估赚" + ArithUtil.mulRound(mul, 0.3));
        }
        holder.setText(R.id.universal_list_rec_shengjizhuan, "升级赚" + ArithUtil.mulRound(mul, 0.8));
    }
}

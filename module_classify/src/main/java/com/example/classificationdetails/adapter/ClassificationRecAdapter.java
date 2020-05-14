package com.example.classificationdetails.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.TBGoodsRecBean;
import com.example.module_classify.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class ClassificationRecAdapter extends MyRecyclerAdapter<TBGoodsRecBean.ResultListBean> {


    public ClassificationRecAdapter(Context context, List<TBGoodsRecBean.ResultListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, TBGoodsRecBean.ResultListBean data, final int position) {
        try {

            holder.setImageFresco(R.id.classification_image, data.getPict_url());
            holder.setText(R.id.classification_name, data.getTitle());
            if (TextUtils.isEmpty(data.getCoupon_amount())) {
                holder.setText(R.id.classification_reduce_price, "可用购物金0元");
            } else {
                holder.setText(R.id.classification_reduce_price, "可用购物金" + data.getCoupon_amount() + "元");
            }

            holder.setText(R.id.classification_preferential_price, "￥" + data.getZk_final_price());
            holder.setText(R.id.classification_original_price, data.getReserve_price());
            // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
            TextView originalPrice = holder.getView(R.id.classification_original_price);
            originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

            TextView immediatelyGrab = holder.getView(R.id.classification_immediately_grab);
            if (viewOnClickListener != null) {
                viewOnClickListener.ViewOnClick(immediatelyGrab, position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.example.refundparticulars.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.AlterationBean;
import com.example.bean.RefundParticularsBean;
import com.example.module_user_mine.R;

import java.util.List;

public class RefundParticularsRecAdapter extends MyRecyclerAdapter<RefundParticularsBean.ItemlistBean> {

    public RefundParticularsRecAdapter(Context context, List<RefundParticularsBean.ItemlistBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RefundParticularsBean.ItemlistBean data, int position) {
        holder.setImageFresco(R.id.refund_particulars_rec_image, data.getProductPic());
        holder.setText(R.id.refund_particulars_rec_goods_name, data.getProductName());
        holder.setText(R.id.refund_particulars_rec_count, "X" + data.getProductQuantity());
        holder.setText(R.id.refund_particulars_rec_productAttr, data.getProductAttr());
        holder.setText(R.id.refund_particulars_rec_money, "￥" + data.getProductPrice());

    }
}

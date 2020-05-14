package com.example.intoshop.baby.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.BabyRecBean;
import com.example.module_classify.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/22
 * Describe:
 */
public class BabyRecAdapter extends MyRecyclerAdapter<BabyRecBean> {

    public BabyRecAdapter(Context context, List<BabyRecBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, BabyRecBean data, int position) {
        holder.setImageResource(R.id.baby_image, data.getImage());
        holder.setText(R.id.baby_name, data.getName());
        holder.setText(R.id.baby_price, data.getPrice());
        holder.setText(R.id.baby_payment_amount, data.getPayment_amount());
        holder.setText(R.id.baby_good_reputation, data.getGood_reputation());
        holder.setText(R.id.baby_shop, data.getShop());
    }
}

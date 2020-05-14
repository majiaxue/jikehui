package com.example.intoshop.baby.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.BabyRecBean;
import com.example.module_classify.R;
import com.example.utils.DisplayUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/22
 * Describe:
 */
public class BabyRecStaggeredAdapter extends MyRecyclerAdapter<BabyRecBean> {


    public BabyRecStaggeredAdapter(Context context, List<BabyRecBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);

    }

    @Override
    public void convert(RecyclerViewHolder holder, BabyRecBean data, int position) {

        if (position == 0) {
            SimpleDraweeView simpleDraweeView = holder.getView(R.id.rec_staggered_image);
            ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
            layoutParams.height = DisplayUtil.dip2px(context, 142);
        }
        holder.setImageResource(R.id.rec_staggered_image, data.getImage());
        holder.setText(R.id.rec_staggered_name, data.getName());
        holder.setText(R.id.rec_staggered_price, data.getPrice());
        holder.setText(R.id.rec_staggered_payment_amount, data.getPayment_amount());
        holder.setText(R.id.rec_staggered_shop, data.getShop());
    }
}

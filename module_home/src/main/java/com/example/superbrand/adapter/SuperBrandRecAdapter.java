package com.example.superbrand.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.SuperBrandBean;
import com.example.module_home.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/5
 * Describe:
 */
public class SuperBrandRecAdapter extends MyRecyclerAdapter<SuperBrandBean> {

    public SuperBrandRecAdapter(Context context, List<SuperBrandBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, SuperBrandBean data, int position) {
        holder.setImageFresco(R.id.super_brand_rec_image, data.getPic());
        holder.setText(R.id.super_brand_rec_name, data.getSellerName());
        holder.setText(R.id.super_brand_rec_average, "平均返佣"+data.getRatio() + "%");
//        holder.setText(R.id.super_brand_rec_rebate,data.getRebate());
    }
}

package com.example.freecharge.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.FreeChargeBean;
import com.example.module_home.R;

import java.util.List;

public class FreeChargeAdapter extends MyRecyclerAdapter<FreeChargeBean> {

    public FreeChargeAdapter(Context context, List<FreeChargeBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, FreeChargeBean data, int position) {

        holder.setImageResource(R.id.free_charge_rec_activity_type, R.drawable.image_tbk);
        holder.setImageFresco(R.id.free_charge_rec_activity_image, data.getTaoPic());
        holder.setText(R.id.free_charge_rec_activity_title, data.getName());
        holder.setText(R.id.free_charge_rec_activity_preferential_price, "￥" + data.getThreshold4());
//        holder.setText(R.id.free_charge_rec_activity_original_price, "￥" + data.getGoodsOriginalPrice());
//        holder.setText(R.id.free_charge_rec_activity_subsidy, "付款" + data.getGoodsPrice() + "元，补贴" + data.getGoodsBackPrice() + "元");
        ProgressBar progressBar = holder.getView(R.id.free_charge_rec_activity_progress);
        progressBar.setMax(data.getNum());
        progressBar.setProgress(data.getNum() - data.getOrderResidueNum());
        holder.setText(R.id.free_charge_rec_activity_order_num, "共" + data.getNum() + "单");
        holder.setText(R.id.free_charge_rec_order_activity_order_residue_num, "剩余" + data.getOrderResidueNum() + "单");
        holder.setText(R.id.free_charge_rec_activity_threshold, data.getThreshold2());

        viewOnClickListener.ViewOnClick(holder.getView(R.id.free_charge_rec_activity_click),position);
    }
}

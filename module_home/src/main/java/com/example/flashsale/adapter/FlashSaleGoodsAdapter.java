package com.example.flashsale.adapter;

import android.content.Context;
import android.widget.ProgressBar;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.FlashSaleGoodsBean;
import com.example.module_home.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FlashSaleGoodsAdapter extends MyRecyclerAdapter<FlashSaleGoodsBean> {

    private String time;

    public FlashSaleGoodsAdapter(Context context, List<FlashSaleGoodsBean> mList, int mLayoutId, String shiDuan) {
        super(context, mList, mLayoutId);
        this.time = shiDuan;
    }

    @Override
    public void convert(RecyclerViewHolder holder, FlashSaleGoodsBean data, int position) {
        ProgressBar progressBar = holder.getView(R.id.flash_sale_goods_rec_progress);
        holder.setImageFresco(R.id.flash_sale_goods_rec_image, data.getPic());
        holder.setText(R.id.flash_sale_goods_rec_name, data.getName());
        holder.setText(R.id.flash_sale_goods_rec_price, "￥" + data.getPrice());

        if (data.getStock() == 0 && data.getBuy_num() == 0) {
            progressBar.setMax(1);
            progressBar.setProgress(1);
        } else {
            progressBar.setMax(data.getStock());
            progressBar.setProgress(data.getBuy_num());
        }

        try {
            holder.setText(R.id.flash_sale_goods_rec_quantity, "已抢" + (data.getBuy_num() / data.getStock()) * 100 + "%");
        } catch (Exception e) {
            e.printStackTrace();
            holder.setText(R.id.flash_sale_goods_rec_quantity, "已抢100%");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");// yyyy年MM月dd日 HH:mm:ss
//        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);


        if (format.equals(data.getShiduan())) {
            holder.setText(R.id.flash_sale_goods_rec_type, "马上抢购");
        } else if (Integer.parseInt(format) > Integer.parseInt(data.getShiduan())) {
            holder.setText(R.id.flash_sale_goods_rec_type, "已结束");
        } else if (Integer.parseInt(format) < Integer.parseInt(data.getShiduan())) {
            holder.setText(R.id.flash_sale_goods_rec_type, "即将开抢");
        }

        viewOnClickListener.ViewOnClick(holder.getView(R.id.flash_sale_goods_rec_type), position);
    }
}

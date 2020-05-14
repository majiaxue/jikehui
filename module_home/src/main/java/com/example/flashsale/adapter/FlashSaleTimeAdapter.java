package com.example.flashsale.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.FlashSaleTimeBean;
import com.example.module_home.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FlashSaleTimeAdapter extends MyRecyclerAdapter<FlashSaleTimeBean> {

    public FlashSaleTimeAdapter(Context context, List<FlashSaleTimeBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, FlashSaleTimeBean data, int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");// yyyy年MM月dd日 HH:mm:ss
//        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        String format = simpleDateFormat.format(date);
        TextView topTime = holder.getView(R.id.flash_sale_top_time);
        TextView topType = holder.getView(R.id.flash_sale_top_type);
        topTime.setText(data.getTime() + ":00");

        if (data.isCheck()) {
            topTime.setTextColor(Color.parseColor("#FFFFFF"));
            topType.setTextColor(Color.parseColor("#FFFFFF"));
            topTime.setTextSize(17f);
            topType.setTextSize(14f);
        } else {
            topTime.setTextColor(Color.parseColor("#e3e3e3"));
            topType.setTextColor(Color.parseColor("#e3e3e3"));
            topTime.setTextSize(15f);
            topType.setTextSize(12f);
        }

        if (format.equals(data.getTime())) {
            topType.setText("正在进行中");
        } else if (Integer.parseInt(format) < Integer.parseInt(data.getTime())) {
            topType.setText("即将开抢");
        } else if (Integer.parseInt(format) > Integer.parseInt(data.getTime())) {
            topType.setText("已结束");
        }

    }
}

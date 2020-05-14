package com.example.logisticsinformation.adapter;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LogisticsInforMationBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/29
 * Describe:
 */
public class LogisticsInforMationAdapter extends MyRecyclerAdapter<LogisticsInforMationBean.TracesBean> {

    private List<LogisticsInforMationBean.TracesBean> list;

    public LogisticsInforMationAdapter(Context context, List<LogisticsInforMationBean.TracesBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
        this.list = mList;
    }

    @Override
    public void convert(RecyclerViewHolder holder, LogisticsInforMationBean.TracesBean data, int position) {
        holder.setText(R.id.accept_station_tv, data.getAcceptStation());
        holder.setText(R.id.accept_station_time, data.getAcceptTime());

        if (position == 0) {
            holder.getView(R.id.time_top_view).setVisibility(View.INVISIBLE);
            holder.setImageResource(R.id.logistics_information_rec_image, R.drawable.icon_yushu);
        } else if (position == list.size() - 1) {
            holder.setImageResource(R.id.logistics_information_rec_image, R.drawable.icon_shou);
            holder.getView(R.id.time_line_view).setVisibility(View.INVISIBLE);
        } else {
            holder.setImageResource(R.id.logistics_information_rec_image, R.drawable.icon_shou);
        }

    }
}

package com.example.local_detail.adapter;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.LocalGoodsBean;
import com.example.user_store.R;

import java.util.List;

public class LocalDetailGoodsAdapter extends MyRecyclerAdapter<LocalGoodsBean.RecordsBean> {
    public LocalDetailGoodsAdapter(Context context, List<LocalGoodsBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LocalGoodsBean.RecordsBean data, int position) {
        holder.setImageUrl(R.id.rv_local_detail_goods_img, data.getPic())
                .setText(R.id.rv_local_detail_goods_name, data.getName())
                .setText(R.id.rv_local_detail_goods_type, data.getDescription() == null ? "" : data.getDescription())
                .setText(R.id.rv_local_detail_goods_price, data.getPrice());

        String isReservation = data.getIsReservation();
        if ("1".equals(isReservation)) {
            holder.getView(R.id.rv_local_detail_goods_time).setVisibility(View.INVISIBLE);
        } else {
            holder.setText(R.id.rv_local_detail_goods_time, data.getStartDate() + "至" + data.getEndDate() + "  |  免预约");
        }
    }
}

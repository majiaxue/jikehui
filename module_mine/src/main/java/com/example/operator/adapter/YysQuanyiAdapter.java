package com.example.operator.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.UserGoodsDetail;
import com.example.module_mine.R;

import java.util.List;

public class YysQuanyiAdapter extends MyRecyclerAdapter<UserGoodsDetail> {
    public YysQuanyiAdapter(Context context, List<UserGoodsDetail> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, UserGoodsDetail data, int position) {
        holder.setImageUrl(R.id.rv_yys_quanyi_img, data.getPic())
                .setText(R.id.rv_yys_quanyi_name, data.getName())
                .setText(R.id.rv_yys_quanyi_price, "￥" + data.getPrice());
    }
}

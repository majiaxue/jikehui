package com.example.adapter;

import android.content.Context;

import com.example.module_base.R;

import java.util.List;

public class PopQuanyiAdapter extends MyRecyclerAdapter<String> {
    private int[] imgArr = {R.drawable.icon_zimai, R.drawable.icon_fenxiangyong, R.drawable.icon_zhijiehuiyuan, R.drawable.icon_pingji, R.drawable.icon_tuandui, R.drawable.icon_jianjie};

    public PopQuanyiAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setImageResource(R.id.rv_pop_quanyi_img, imgArr[position])
                .setText(R.id.rv_pop_quanyi_txt, data);
    }
}

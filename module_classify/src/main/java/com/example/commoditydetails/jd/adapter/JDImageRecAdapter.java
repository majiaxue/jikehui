package com.example.commoditydetails.jd.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_classify.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/15
 * Describe:
 */
public class JDImageRecAdapter extends MyRecyclerAdapter<String> {

    public JDImageRecAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setImageUrl(R.id.commodity_details_rec_image,"https:"+data);
    }
}

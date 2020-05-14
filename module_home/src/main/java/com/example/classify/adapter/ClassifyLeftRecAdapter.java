package com.example.classify.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.LeftGroupBean;
import com.example.module_home.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/28
 * Describe:
 */
public class ClassifyLeftRecAdapter extends MyRecyclerAdapter<LeftGroupBean> {

    public ClassifyLeftRecAdapter(Context context, List<LeftGroupBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, LeftGroupBean data, int position) {
        holder.setText(R.id.group_name, data.getName());
        if (data.isSelected()) {
            //选中
            holder.getView(R.id.group_select_bg).setVisibility(View.VISIBLE);
            holder.setTextColor(R.id.group_name,Color.parseColor("#fc5917"));
            holder.getView(R.id.group_bg).setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            //未选中
            holder.getView(R.id.group_select_bg).setVisibility(View.GONE);
            holder.setTextColor(R.id.group_name,Color.parseColor("#666666"));
            holder.getView(R.id.group_bg).setBackgroundColor(Color.parseColor("#00000000"));
        }
    }
}

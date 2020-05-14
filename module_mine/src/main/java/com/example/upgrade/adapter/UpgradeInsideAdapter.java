package com.example.upgrade.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_mine.R;

import java.util.List;

public class UpgradeInsideAdapter extends MyRecyclerAdapter<String> {
    public UpgradeInsideAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setTextFormHtml(R.id.rv_upgrade_inside_txt, data);
    }
}

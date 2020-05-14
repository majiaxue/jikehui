package com.example.assess.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.AssessTitleBean;
import com.example.user_store.R;
import com.example.utils.TxtUtil;

import java.util.List;

public class AssessTitleAdapter extends MyRecyclerAdapter<AssessTitleBean> {
    public AssessTitleAdapter(Context context, List<AssessTitleBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, AssessTitleBean data, int position) {
        holder.setText(R.id.rv_assess_title_txt, data.getTitle());
        if (data.isCheck()) {
            TxtUtil.txtJianbian((TextView) holder.getView(R.id.rv_assess_title_txt), "#fb4119", "#febf0d");
            holder.setBackgroundResource(R.id.rv_assess_title_txt, R.drawable.icon_quanbu);
        } else {
            TxtUtil.txtJianbian((TextView) holder.getView(R.id.rv_assess_title_txt), "#333333", "#333333");
            holder.setBackgroundResource(R.id.rv_assess_title_txt, R.drawable.bg_11_666_border);
        }
        if (viewOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.rv_assess_title_parent), position);
        }
    }
}

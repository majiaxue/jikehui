package com.example.suggestion_history.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.FeedBackHistoryBean;
import com.example.module_mine.R;

import java.util.List;

public class SuggestionHistoryAdapter extends MyRecyclerAdapter<FeedBackHistoryBean> {
    public SuggestionHistoryAdapter(Context context, List<FeedBackHistoryBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, FeedBackHistoryBean data, int position) {
        holder.setText(R.id.rv_suggestion_history_feedback_content, data.getMessage())
                .setText(R.id.rv_suggestion_history_feedback_time, data.getCreateTime())
                .setText(R.id.rv_suggestion_history_reply_content, data.getBackMessage())
                .setText(R.id.rv_suggestion_history_reply_time, data.getDealTime());
        RelativeLayout rela = holder.getView(R.id.rv_suggestion_history_rela);
        if (data.getBackMessage() == null || "".equals(data.getBackMessage())) {
            rela.setVisibility(View.GONE);
        } else {
            rela.setVisibility(View.VISIBLE);
        }
    }
}

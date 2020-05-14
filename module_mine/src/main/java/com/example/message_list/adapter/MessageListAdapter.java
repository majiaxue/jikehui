package com.example.message_list.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.MessageListBean;
import com.example.module_mine.R;

import java.util.List;

public class MessageListAdapter extends MyRecyclerAdapter<MessageListBean> {
    public MessageListAdapter(Context context, List<MessageListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MessageListBean data, int position) {
        holder.setText(R.id.rv_message_list_title, data.getTitle())
                .setText(R.id.rv_message_list_centent, data.getContent())
                .setText(R.id.rv_message_list_time, data.getTime());
    }
}

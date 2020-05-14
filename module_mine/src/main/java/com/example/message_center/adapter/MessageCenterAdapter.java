package com.example.message_center.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.MessageCenterBean;
import com.example.module_mine.R;

import java.util.List;

public class MessageCenterAdapter extends BaseAdapter {
    private Context context;
    private List<MessageCenterBean> dataList;

    public MessageCenterAdapter(Context context, List<MessageCenterBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            if ("0".equals(dataList.get(position).getFlag())) {
                convertView = LayoutInflater.from(context).inflate(R.layout.lst_message_center, parent, false);
                holder.title = convertView.findViewById(R.id.lst_message_center_title);
                holder.messageType = convertView.findViewById(R.id.lst_message_center_type);
                holder.content = convertView.findViewById(R.id.lst_message_center_content);
                holder.express = convertView.findViewById(R.id.lst_message_center_express);
                holder.img = convertView.findViewById(R.id.lst_message_center_img);
                holder.status = convertView.findViewById(R.id.lst_message_center_status);
            } else if ("1".equals(dataList.get(position).getFlag())) {
                convertView = LayoutInflater.from(context).inflate(R.layout.lst_message_center2, parent, false);
                holder.title = convertView.findViewById(R.id.lst_message_center2_title);
                holder.messageType = convertView.findViewById(R.id.lst_message_center2_type);
                holder.content = convertView.findViewById(R.id.lst_message_center2_content);
                holder.status = convertView.findViewById(R.id.lst_message_center2_status);
            }
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.title.setText(dataList.get(position).getTitle());
//        holder.messageType.setText(dataList.get(position).getMessageType());
        holder.content.setText(dataList.get(position).getMessage());
//        holder.status.setText(dataList.get(position).getStatus());
        if ("0".equals(dataList.get(position).getFlag())) {
            Glide.with(context).load(dataList.get(position).getGoodsUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners((int) context.getResources().getDimension(R.dimen.dp_5)))).into(holder.img);
        }
        return convertView;
    }

    private class Holder {
        private TextView title;
        private TextView messageType;
        private TextView status;
        private TextView content;
        private TextView express;
        private ImageView img;
    }
}

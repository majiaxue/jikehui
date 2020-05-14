package com.example.operator_gain.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_home.R;

import java.util.List;

public class OperatorGainQuanyiAdapter extends MyRecyclerAdapter<String> {
    public OperatorGainQuanyiAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        if (position == 0) {
            holder.setImageResource(R.id.rv_vp_operator_gain_img, R.drawable.icon_jinpaihuiyuan);
            holder.setText(R.id.rv_vp_operator_gain_txt1, data);
            holder.setText(R.id.rv_vp_operator_gain_txt2, "所有权益");
        } else if (position == 1) {
            holder.setImageResource(R.id.rv_vp_operator_gain_img, R.drawable.icon_zimai1);
            holder.setText(R.id.rv_vp_operator_gain_txt1, "自买返佣");
            holder.setText(R.id.rv_vp_operator_gain_txt2, "收益提升" + data + "%");
        } else if (position == 2) {
            holder.setImageResource(R.id.rv_vp_operator_gain_img, R.drawable.icon_fenxiang2);
            holder.setText(R.id.rv_vp_operator_gain_txt1, "分享返佣");
            holder.setText(R.id.rv_vp_operator_gain_txt2, "收益提升" + data + "%");
        } else if (position == 3) {
            holder.setImageResource(R.id.rv_vp_operator_gain_img, R.drawable.icon_zhijiehuiyuan1);
            holder.setText(R.id.rv_vp_operator_gain_txt1, "直接会员出单");
            if (data == null) {
                holder.setText(R.id.rv_vp_operator_gain_txt2, "奖励0%");
            } else {
                String[] split = data.split(",");
                holder.setText(R.id.rv_vp_operator_gain_txt2, "奖励" + split[0] + "%");
            }
        } else if (position == 4) {
            holder.setImageResource(R.id.rv_vp_operator_gain_img, R.drawable.icon_jianjiehuiyuan);
            holder.setText(R.id.rv_vp_operator_gain_txt1, "间接会员出单");
            if (data == null) {
                holder.setText(R.id.rv_vp_operator_gain_txt2, "奖励0%");
            } else {
                String[] split = data.split(",");
                if (split.length == 1) {
                    holder.setText(R.id.rv_vp_operator_gain_txt2, "奖励0%");
                } else {
                    holder.setText(R.id.rv_vp_operator_gain_txt2, "奖励" + split[1] + "%");
                }
            }
        } else if (position == 5) {
            holder.setImageResource(R.id.rv_vp_operator_gain_img, R.drawable.icon_zhishuttuandui);
            holder.setText(R.id.rv_vp_operator_gain_txt1, "直属团队");
            holder.setText(R.id.rv_vp_operator_gain_txt2, "奖励" + data + "%");
        }
    }
}

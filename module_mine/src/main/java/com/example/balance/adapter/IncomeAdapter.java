package com.example.balance.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.InAndOutBean;
import com.example.entity.MessageListBean;
import com.example.module_mine.R;

import java.util.List;

public class IncomeAdapter extends MyRecyclerAdapter<InAndOutBean.RecordsBean> {
    private int flag;

    public IncomeAdapter(Context context, List<InAndOutBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, InAndOutBean.RecordsBean data, int position) {
        holder.setText(R.id.rv_income_title, data.getNote())
                .setText(R.id.rv_income_time, data.getCreateTime())
                .setText(R.id.rv_income_status, data.getType() == 0 ? "到账成功" : "提现成功")
                .setText(R.id.rv_income_money, data.getType() == 0 ? "+" + data.getPrice() : "-" + data.getPrice());
    }
}

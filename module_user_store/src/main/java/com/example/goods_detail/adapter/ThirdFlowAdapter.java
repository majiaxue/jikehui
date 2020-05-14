package com.example.goods_detail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.bean.ChooseInsideBean;
import com.example.user_store.R;
import com.example.utils.OnFlowSelectListener;
import com.example.view.flowLayout.FlowLayout;
import com.example.view.flowLayout.TagAdapter;

import java.util.List;

public class ThirdFlowAdapter extends TagAdapter<ChooseInsideBean> {
    private Context context;
    private OnFlowSelectListener listener;

    public ThirdFlowAdapter(List<ChooseInsideBean> datas) {
        super(datas);
    }

    public ThirdFlowAdapter(List<ChooseInsideBean> datas, Context context, OnFlowSelectListener listener) {
        super(datas);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public View getView(FlowLayout parent, int position, ChooseInsideBean chooseInsideBean) {
        TextView txt = (TextView) LayoutInflater.from(context).inflate(R.layout.pop_choose_goods_size, parent, false);
        txt.setText(chooseInsideBean.getContent());
        return txt;
    }

    @Override
    public void onSelected(int position, View view) {
        super.onSelected(position, view);
        view.setBackgroundResource(R.drawable.goods_5_99fd3c15);
        listener.setOnFlowSelect(position);
    }

    @Override
    public void unSelected(int position, View view) {
        super.unSelected(position, view);
        view.setBackgroundResource(R.drawable.goods_5_e5e5e5);
    }
}

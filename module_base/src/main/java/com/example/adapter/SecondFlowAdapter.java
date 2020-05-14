package com.example.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.bean.ChooseInsideBean;
import com.example.module_base.R;
import com.example.view.flowLayout.FlowLayout;
import com.example.view.flowLayout.TagAdapter;

import java.util.List;

public class SecondFlowAdapter extends TagAdapter<ChooseInsideBean> {
    private Context context;

    public SecondFlowAdapter(List<ChooseInsideBean> datas) {
        super(datas);
    }

    public SecondFlowAdapter(List<ChooseInsideBean> datas, Context context) {
        super(datas);
        this.context = context;
    }

    @Override
    public View getView(FlowLayout parent, int position, ChooseInsideBean chooseInsideBean) {
        TextView txt = (TextView) LayoutInflater.from(context).inflate(R.layout.pop_choose_goods_size, parent, false);
        txt.setText(chooseInsideBean.getContent());

        if (!chooseInsideBean.isCanClick()) {
            txt.setBackgroundResource(R.drawable.goods_5_canotclick);
            txt.setTextColor(Color.parseColor("#ffffff"));
        }
        return txt;
    }

    @Override
    public void onSelected(int position, View view) {
        super.onSelected(position, view);
//        view.setBackgroundResource(R.drawable.goods_5_99fd3c15);
        ((TextView) view).setTextColor(Color.parseColor("#ffffff"));
    }

    @Override
    public void unSelected(int position, View view) {
        super.unSelected(position, view);
//        view.setBackgroundResource(R.drawable.goods_5_e5e5e5);
        ((TextView) view).setTextColor(Color.parseColor("#333333"));
    }
}

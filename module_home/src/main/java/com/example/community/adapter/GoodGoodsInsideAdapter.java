package com.example.community.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.GoodGoodsBean;
import com.example.module_home.R;

import java.util.List;

public class GoodGoodsInsideAdapter extends MyRecyclerAdapter<GoodGoodsBean.NetBean.ItemDataBean> {
    public GoodGoodsInsideAdapter(Context context, List<GoodGoodsBean.NetBean.ItemDataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, GoodGoodsBean.NetBean.ItemDataBean data, int position) {
        holder.setImageUrl(R.id.rv_goods_commend_inside_img, data.getItempic());
    }
}

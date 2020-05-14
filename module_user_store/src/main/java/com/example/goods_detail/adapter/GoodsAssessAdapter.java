package com.example.goods_detail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.AssessBean;
import com.example.user_store.R;

import java.util.List;

public class GoodsAssessAdapter extends MyRecyclerAdapter<AssessBean.RecordsBean> {
    public GoodsAssessAdapter(Context context, List<AssessBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, AssessBean.RecordsBean data, int position) {
        holder.setText(R.id.rv_goods_assess_name, "1".equals(data.getIsAnonymous()) ? "匿名用户" : data.getNickname())
                .setText(R.id.rv_goods_assess_content, data.getInfo())
                .setImageUrl(R.id.rv_goods_assess_img, data.getIcon());
    }
}

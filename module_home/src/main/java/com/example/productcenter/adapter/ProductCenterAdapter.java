package com.example.productcenter.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.ProductCenterBean;
import com.example.module_home.R;

import java.util.List;

public class ProductCenterAdapter extends MyRecyclerAdapter<ProductCenterBean.RecordsBean> {

    public ProductCenterAdapter(Context context, List<ProductCenterBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ProductCenterBean.RecordsBean data, int position) {
        TextView ranking = holder.getView(R.id.product_center_rec_ranking);
        if (0 == position) {
            ranking.setText("1");
            ranking.setBackground(context.getResources().getDrawable(R.drawable.cpzx_icon_1));
        } else if (1 == position) {
            ranking.setText("2");
            ranking.setBackground(context.getResources().getDrawable(R.drawable.cpzx_icon_2));
        } else if (2 == position) {
            ranking.setText("3");
            ranking.setBackground(context.getResources().getDrawable(R.drawable.cpzx_icon_3));
        }
        holder.setImageFresco(R.id.product_center_rec_pic, data.getLogo())
                .setText(R.id.product_center_rec_name, data.getTitle())
                .setText(R.id.product_center_rec_price, "￥" + data.getPrice())
                .setText(R.id.product_center_rec_intro, data.getMessage());
    }
}

package com.example.goodscollection.adapter;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.GoodsCollectionRecBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/22
 * Describe:
 */
public class GoodsCollectionRecAdapter extends MyRecyclerAdapter<GoodsCollectionRecBean.RecordsBean> {

    private boolean isCompile;

    public GoodsCollectionRecAdapter(Context context, List<GoodsCollectionRecBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public GoodsCollectionRecAdapter(Context context, List<GoodsCollectionRecBean.RecordsBean> mList, int mLayoutId, boolean isCompile) {
        super(context, mList, mLayoutId);
        this.isCompile = isCompile;
    }

    @Override
    public void convert(RecyclerViewHolder holder, GoodsCollectionRecBean.RecordsBean data, int position) {
        if (isCompile) {
            holder.getView(R.id.goods_collection_check).setVisibility(View.VISIBLE);
        } else {
            holder.getView(R.id.goods_collection_check).setVisibility(View.GONE);
        }
        if (data.isCheck()) {
            holder.setImageResource(R.id.goods_collection_check, R.drawable.icon_xuanzhong);
        } else {
            holder.setImageResource(R.id.goods_collection_check, R.drawable.icon_weixuanzhong);
        }

        holder.setImageFresco(R.id.goods_collection_image, data.getPic());
        holder.setText(R.id.goods_collection_name, data.getName());
        holder.setText(R.id.goods_collection_price, "￥" + data.getPrice());
        holder.setText(R.id.goods_collection_payment_amount, data.getSale() + "人付款");
        holder.setText(R.id.goods_collection_good_reputation, data.getGoodReputation() + "好评");
        holder.setText(R.id.goods_collection_shop, data.getSellerName());
        viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.goods_collection_check), holder.getView(R.id.goods_collection_go_shop), position);
    }


    public void setCompile(boolean compile) {
        isCompile = compile;
        notifyDataSetChanged();
    }
}

package com.example.shopcollect.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_user_mine.R;
import com.example.bean.ShopCollectBean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class ShopCollectAdapter extends MyRecyclerAdapter<ShopCollectBean.RecordsBean> {
    public ShopCollectAdapter(Context context, List<ShopCollectBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ShopCollectBean.RecordsBean data, int position) {
        holder.setImageFresco(R.id.shop_collect_rec_image,data.getSellerLogo());
        holder.setText(R.id.shop_collect_rec_name,data.getSellerShopName());
        viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.shop_collect_rec_menu),holder.getView(R.id.shop_collect_rec_un_follow),position);
    }
}

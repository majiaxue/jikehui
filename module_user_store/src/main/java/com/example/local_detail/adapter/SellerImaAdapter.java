package com.example.local_detail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_store.R;

import java.util.List;

public class SellerImaAdapter extends MyRecyclerAdapter<String> {
    public SellerImaAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setImageUrl(R.id.rv_local_seller_rv_img, data);
    }
}

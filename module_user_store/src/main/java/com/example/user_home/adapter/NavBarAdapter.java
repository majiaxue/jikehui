package com.example.user_home.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.NavBarBean;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.user_store.R;

import java.util.List;

public class NavBarAdapter extends MyRecyclerAdapter<NavBarBean.RecordsBean> {
    public NavBarAdapter(Context context, List<NavBarBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, NavBarBean.RecordsBean data, int position) {
        holder.setText(R.id.rv_navbar_name, data.getName())
                .setImageUrl(R.id.rv_navbar_img, data.getIcon());

    }
}

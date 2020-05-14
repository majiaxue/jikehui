package com.example.location.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.user_store.R;

import java.util.List;

public class LocationHotCityAdapter extends MyRecyclerAdapter<String> {

    public LocationHotCityAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        holder.setText(R.id.location_hot_city_name,data);
    }
}

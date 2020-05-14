package com.example.location.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.CitiesBean;
import com.example.bean.RegionBean;
import com.example.common.CommonResource;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.R;
import com.example.utils.CitySPUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class RegionAdapter extends MyRecyclerAdapter<RegionBean.CityBean> {

    private static int RESULTCODE = 0;

    public RegionAdapter(Context context, List<RegionBean.CityBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, final RegionBean.CityBean data, int position) {

        holder.setText(R.id.item_letter, data.getLabel());
        RecyclerView itemRegionList = holder.getView(R.id.item_regionList);
        TextView checkText = holder.getView(R.id.item_check_text);
        if (position == 0) {
            checkText.setVisibility(View.VISIBLE);
//            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
//            LocationHotCityAdapter locationHotCityAdapter = new LocationHotCityAdapter(context, data.getRegion(), R.layout.item_location_hot_city_rec);
//            itemRegionList.setLayoutManager(gridLayoutManager);
//            itemRegionList.setAdapter(locationHotCityAdapter);
//            locationHotCityAdapter.setOnItemClick(new OnItemClickListener() {
//                @Override
//                public void onItemClick(RecyclerView parent, View view, int position) {
//
//
//                }
//            });
            if (data.getRegion().size()==0){
                itemRegionList.setVisibility(View.GONE);
            }else{
                itemRegionList.setVisibility(View.VISIBLE);
            }
        } else if (position > 0 && position < 3) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
            LocationHotCityAdapter locationHotCityAdapter = new LocationHotCityAdapter(context, data.getRegion(), R.layout.item_location_hot_city_rec);
            itemRegionList.setLayoutManager(gridLayoutManager);
            itemRegionList.setAdapter(locationHotCityAdapter);
            locationHotCityAdapter.setOnItemClick(new OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerView parent, View view, int position) {
                    TextView hotCityName = view.findViewById(R.id.location_hot_city_name);
                    CitySPUtil.addParm(CommonResource.CITY, hotCityName.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("cityName", hotCityName.getText().toString());
                    ((Activity) context).setResult(RESULTCODE, intent);
                    ((Activity) context).finish();
                }
            });
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            LocationSelectAdapter locationSelectAdapter = new LocationSelectAdapter(context, data.getRegion(), R.layout.item_location_select_rec);
            itemRegionList.setLayoutManager(linearLayoutManager);
            itemRegionList.setAdapter(locationSelectAdapter);
            locationSelectAdapter.setOnItemClick(new OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerView parent, View view, int position) {
                    TextView itemText = view.findViewById(R.id.item_text);
                    CitySPUtil.addParm(CommonResource.CITY, itemText.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("cityName", itemText.getText().toString());
                    ((Activity) context).setResult(RESULTCODE, intent);
                    ((Activity) context).finish();
                }
            });
        }

    }


}

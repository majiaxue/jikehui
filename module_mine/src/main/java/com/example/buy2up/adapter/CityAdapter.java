package com.example.buy2up.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.City2Bean;
import com.example.module_mine.R;
import static com.example.buy2up.adapter.JDConst.INDEX_INVALID;
import static com.example.buy2up.adapter.JDConst.INDEX_TAB_AREA;
import static com.example.buy2up.adapter.JDConst.INDEX_TAB_CITY;
import static com.example.buy2up.adapter.JDConst.INDEX_TAB_PROVINCE;
import java.util.List;

public class CityAdapter extends BaseAdapter {

    Context context;

    List<City2Bean> mCityList;

    private int cityIndex = INDEX_INVALID;

    public CityAdapter(Context context, List<City2Bean> mCityList) {
        this.context = context;
        this.mCityList = mCityList;
    }

    public int getSelectedPosition() {
        return this.cityIndex;
    }

    public void updateSelectedPosition(int index) {
        this.cityIndex = index;
    }

    @Override
    public int getCount() {
        return mCityList.size();
    }

    @Override
    public City2Bean getItem(int position) {
        return mCityList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return Long.parseLong(mCityList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pop_jdcitypicker_item, parent, false);

            holder = new Holder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            //holder.selectImg = (ImageView) convertView.findViewById(R.id.selectImg);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        City2Bean item = getItem(position);
        holder.name.setText(item.getName());

        boolean checked = cityIndex != INDEX_INVALID && mCityList.get(cityIndex).getId().equals(item.getId());
        holder.name.setEnabled(!checked);
        holder.selectImg.setVisibility(checked ? View.VISIBLE : View.GONE);


        return convertView;
    }


    class Holder {
        TextView name;
        ImageView selectImg;
    }
}

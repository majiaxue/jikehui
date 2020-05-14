package com.example.buy2up.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.DistrictBean;
import com.example.module_mine.R;

import java.util.List;
import static com.example.buy2up.adapter.JDConst.INDEX_INVALID;
import static com.example.buy2up.adapter.JDConst.INDEX_TAB_AREA;
import static com.example.buy2up.adapter.JDConst.INDEX_TAB_CITY;
import static com.example.buy2up.adapter.JDConst.INDEX_TAB_PROVINCE;
public class AreaAdapter extends BaseAdapter{

    Context context;

    List<DistrictBean> mDistrictList;

    private int districtIndex = INDEX_INVALID;

    public AreaAdapter(Context context, List<DistrictBean> mDistrictList) {
        this.context = context;
        this.mDistrictList = mDistrictList;
    }


    public int getSelectedPosition() {
        return this.districtIndex;
    }

    public void updateSelectedPosition(int index) {
        this.districtIndex = index;
    }

    @Override
    public int getCount() {
        return mDistrictList.size();
    }

    @Override
    public DistrictBean getItem(int position) {
        return mDistrictList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return Long.parseLong(mDistrictList.get(position).getId());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pop_jdcitypicker_item, parent, false);

            holder = new Holder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
           // holder.selectImg = (ImageView) convertView.findViewById(R.id.selectImg);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        DistrictBean item = getItem(position);
        holder.name.setText(item.getName());

        boolean checked = districtIndex != INDEX_INVALID && mDistrictList.get(districtIndex).getId().equals(item.getId());
        holder.name.setEnabled(!checked);
       // holder.selectImg.setVisibility(checked ? View.VISIBLE : View.GONE);


        return convertView;
    }


    class Holder {
        TextView name;
       // ImageView selectImg;
    }
}

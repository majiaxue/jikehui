package com.example.shippingaddress.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_user_mine.R;
import com.example.bean.ShippingAddressBean;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public class ShippingAddressAdapter extends MyRecyclerAdapter<ShippingAddressBean> {

    public ShippingAddressAdapter(Context context, List<ShippingAddressBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ShippingAddressBean data, int position) {
        if (data.getAddressDefault() == 1) {
            holder.setImageResource(R.id.shipping_address_check, R.drawable.icon_xuanzhong20);
        } else {
            holder.setImageResource(R.id.shipping_address_check, R.drawable.icon_weixuanzhong20);
        }

        if (data.getAddressTips() == 1) {
            holder.setText(R.id.shipping_address_biaoqian, "家");
        } else if (data.getAddressTips() == 2) {
            holder.setText(R.id.shipping_address_biaoqian, "公司");
        } else {
            holder.setText(R.id.shipping_address_biaoqian, "学校");
        }

        holder.setText(R.id.shipping_address_name, data.getAddressName());
        holder.setText(R.id.shipping_address_phone, data.getAddressPhone());
        holder.setText(R.id.shipping_address_site, data.getAddressProvince() + data.getAddressCity() + data.getAddressArea() + data.getAddressDetail());

        viewThreeOnClickListener.ViewThreeOnClick(holder.getView(R.id.shipping_address_check), holder.getView(R.id.shipping_address_amend), holder.getView(R.id.shipping_address_delete), position);
    }
}

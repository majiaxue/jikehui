package com.example.browse_record.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.MyCollectBean;
import com.example.module_mine.R;
import com.example.utils.ArithUtil;

import java.util.List;

public class BrowseRecordAdapter extends MyRecyclerAdapter<MyCollectBean> {
    public BrowseRecordAdapter(Context context, List<MyCollectBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyCollectBean data, int position) {
        if (data.getNormalPrice() != null && data.getNormalPrice().contains("-")) {
            String[] split = data.getNormalPrice().split("-");
            data.setNormalPrice(split[0]);
        }

        if (data.getType() == 0) {
            //淘宝
            holder.setImageResource(R.id.rv_collection_type, R.drawable.taobao);
            holder.setText(R.id.rv_collection_name, data.getGoodsName());
            String normalPrice = data.getNormalPrice();
            boolean contains = normalPrice.contains("-");
            if (contains) {
                String[] split = normalPrice.split("-");
                holder.setText(R.id.rv_collection_preferential_price, "￥" + split[0]);
            } else {
                holder.setText(R.id.rv_collection_preferential_price, "￥" + normalPrice);
            }
            holder.setText(R.id.rv_collection_number, "已抢" + (data.getQuantity().split("\\.")[0]) + "件");
            holder.setImageFresco(R.id.rv_collection_image, data.getImage());
        } else if (data.getType() == 1) {
            //京东
            holder.setImageResource(R.id.rv_collection_type, R.drawable.jingdong);
            holder.setText(R.id.rv_collection_name, data.getGoodsName());
            holder.setText(R.id.rv_collection_preferential_price, "￥" + data.getNormalPrice());
            if (!TextUtils.isEmpty(data.getQuantity())) {
                holder.setText(R.id.rv_collection_number, "已抢" + data.getQuantity() + "件");
            } else {
                holder.setText(R.id.rv_collection_number, "已抢0件");
            }

            holder.setImageFresco(R.id.rv_collection_image, data.getImage());
        } else if (data.getType() == 2) {
            //拼多多
            holder.setImageResource(R.id.rv_collection_type, R.drawable.pinduoduo);
            holder.setText(R.id.rv_collection_name, data.getGoodsName())
                    .setText(R.id.rv_collection_preferential_price, "￥" + ArithUtil.exact(data.getGroupPrice() == null ? 0 : data.getGroupPrice() * 0.01, 1))
                    .setText(R.id.rv_collection_number, "已抢" + data.getQuantity() + "件")
                    .setImageFresco(R.id.rv_collection_image, data.getImage());
        }
    }
}

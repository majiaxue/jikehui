package com.example.commoditydetails.pdd.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_classify.R;
import com.example.utils.DisplayUtil;
import com.example.utils.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/11
 * Describe:
 */
public class CommodityDetailsRecAdapter extends MyRecyclerAdapter<String> {

    public CommodityDetailsRecAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        SimpleDraweeView image = holder.getView(R.id.commodity_details_rec_image);
        FrescoUtils.setControllerListener(image,data, DisplayUtil.getScreenWidth(context));

    }
}

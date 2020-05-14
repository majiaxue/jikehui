package com.example.superbrand.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.RestsBean;
import com.example.module_home.R;
import com.example.utils.SPUtil;

import java.util.List;

public class RestsAdapter extends MyRecyclerAdapter<RestsBean.DataBeanX> {


    public RestsAdapter(Context context, List<RestsBean.DataBeanX> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, final RestsBean.DataBeanX data, int position) {
        holder.setImageFresco(R.id.rests_rec_shop_icon, data.getBrand_logo());
        holder.setText(R.id.rests_rec_shop_name, data.getTb_brand_name());

        viewOnClickListener.ViewOnClick(holder.getView(R.id.rests_rec_shop_id), position);


        RecyclerView goodsRec = holder.getView(R.id.rests_rec_goods_rec);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
        goodsRec.setLayoutManager(gridLayoutManager);
        RestsGoodsAdapter restsGoodsAdapter = new RestsGoodsAdapter(context, data.getItem(), R.layout.itme_rests_rec_goods_rec);
        goodsRec.setAdapter(restsGoodsAdapter);

        restsGoodsAdapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                if (!TextUtils.isEmpty(SPUtil.getToken())) {
                    ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                            .withString("para", data.getItem().get(position).getItemid())
                            .withString("commission_rate", data.getItem().get(position).getTkrates())
                            .withInt("type", 0)
                            .navigation();
                } else {
                    ARouter.getInstance().build("/mine/login").navigation();
                }

            }
        });
    }
}

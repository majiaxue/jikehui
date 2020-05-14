package com.example.community.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.GoodGoodsBean;
import com.example.module_home.R;
import com.example.utils.ArithUtil;
import com.example.utils.MyTimeUtil;
import com.example.utils.SPUtil;

import java.util.List;

public class GoodGoodsAdapter extends MyRecyclerAdapter<GoodGoodsBean.NetBean> {
    public GoodGoodsAdapter(Context context, List<GoodGoodsBean.NetBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, final GoodGoodsBean.NetBean data, int position) {
        holder.setImageUrl(R.id.rv_good_goods_head, data.getApp_hot_image())
                .setText(R.id.rv_good_goods_name, data.getName())
                .setText(R.id.rv_good_goods_time, MyTimeUtil.date2StringLong(data.getActivity_start_time()))
                .setText(R.id.rv_good_goods_content, data.getCopy_text() == null ? "" : data.getCopy_text().replaceAll("&lt;br&gt;", "\n"));

        RecyclerView rv = holder.getView(R.id.rv_good_goods_img);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);

        List<GoodGoodsBean.NetBean.ItemDataBean> item_data = data.getItem_data();

        GoodGoodsInsideAdapter adapter = new GoodGoodsInsideAdapter(context, item_data, R.layout.rv_goods_commend_inside);
        rv.setAdapter(adapter);

        adapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
//                new TBUtil().openTbWithGoodsId(context, data.getItem_data().get(position).getItemid());
                if (!TextUtils.isEmpty(SPUtil.getToken())) {
                    String startTime = MyTimeUtil.date2String(data.getItem_data().get(position).getCouponstarttime() + "000");
                    String endTime = MyTimeUtil.date2String(data.getItem_data().get(position).getCouponendtime() + "000");
                    ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                            .withString("para", data.getItem_data().get(position).getItemid())
                            .withString("shoptype", data.getItem_data().get(position).getShoptype())
                            .withDouble("youhuiquan", Double.valueOf(data.getItem_data().get(position).getCouponmoney()))
                            .withString("coupon_start_time", startTime)
                            .withString("coupon_end_time", endTime)
                            .withString("commission_rate", ArithUtil.mul(data.getItem_data().get(position).getTkrates(),100) + "")
                            .withInt("type", 0)
                            .navigation();
                }
            }
        });

    }
}

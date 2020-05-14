package com.example.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.bean.HotSaleBean;
import com.example.common.CommonResource;
import com.example.module_base.R;
import com.example.utils.ArithUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.SPUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/22
 * Describe:
 */
public class BaseRecStaggeredAdapter extends MyRecyclerAdapter<HotSaleBean.DataBean> {


    public BaseRecStaggeredAdapter(Context context, List<HotSaleBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);

    }

    @Override
    public void convert(RecyclerViewHolder holder, HotSaleBean.DataBean data, int position) {

        if (position == 0) {
            SimpleDraweeView simpleDraweeView = holder.getView(R.id.base_staggered_image);
            ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
            layoutParams.height = DisplayUtil.dip2px(context, 142);
        }
        double returnRatio = data.getReturnRatio();
        double price = data.getPrice();
        double predict = ArithUtil.mul(ArithUtil.mul(returnRatio / 100, price), SPUtil.getFloatValue(CommonResource.BACKBL));
//        double predict = ArithUtil.exact(returnRatio / 100 * price * SPUtil.getFloatValue(CommonResource.BACKBL), 2);
        holder.setText(R.id.base_staggered_name, data.getName())
                .setText(R.id.base_staggered_price, "￥" + data.getPrice())
                .setText(R.id.base_staggered_payment_amount, data.getSale() + "人付款")
                .setText(R.id.base_staggered_shop, data.getSellerName())
                .setText(R.id.base_staggered_payment_amount_predict, "预估赚￥" + predict)
                .setImageFresco(R.id.base_staggered_image, data.getPic());
        if (viewTwoOnClickListener != null) {
            viewOnClickListener.ViewOnClick(holder.getView(R.id.base_staggered_go_shop), position);
        }
    }
}

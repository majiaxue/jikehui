package com.example.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.GoodChoiceBean;
import com.example.module_home.R;
import com.example.utils.ArithUtil;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class GoodChoiceRecAdapter extends MyRecyclerAdapter<GoodChoiceBean.DataBean> {

    public GoodChoiceRecAdapter(Context context, List<GoodChoiceBean.DataBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, GoodChoiceBean.DataBean data, int position) {
        holder.setImageFresco(R.id.good_choice_image, data.getItempic());
        holder.setText(R.id.good_choice_name, data.getItemtitle());
        holder.setText(R.id.good_choice_preferential_price, "￥" + data.getItemendprice());
        holder.setText(R.id.good_choice_original_price, data.getItemprice());
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        TextView originalPrice = holder.getView(R.id.good_choice_original_price);
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }
}

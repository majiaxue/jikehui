package com.example.operator_gain.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.UserGoodsDetail;
import com.example.module_home.R;

import java.util.List;

public class OperatorGainBottomAdapter extends MyRecyclerAdapter<UserGoodsDetail> {
    public OperatorGainBottomAdapter(Context context, List<UserGoodsDetail> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, UserGoodsDetail data, int position) {
        holder.setText(R.id.rv_yys_quanyi_name, data.getName())
                .setText(R.id.rv_yys_quanyi_price, "￥" + data.getPrice());

        ImageView view = holder.getView(R.id.rv_yys_quanyi_img);
        RequestOptions requestOptions = RequestOptions.centerCropTransform();
        Glide.with(context).load(data.getPic()).apply(requestOptions).transform(new RoundedCorners((int) context.getResources().getDimension(R.dimen.dp_5))).into(view);
    }
}

package com.example.assess.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.AssessBean;
import com.example.user_store.R;
import com.example.utils.SpaceItemDecoration;
import com.example.view.RatingBarView;

import java.util.Arrays;
import java.util.List;

public class AssessAdapter extends MyRecyclerAdapter<AssessBean.RecordsBean> {
    private AssessImageAdapter imageAdapter;

    public AssessAdapter(Context context, List<AssessBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(final RecyclerViewHolder holder, AssessBean.RecordsBean data, final int position) {
        holder.setImageUrlCircular(R.id.rv_assess_header, data.getIcon())
                .setText(R.id.rv_assess_name, "1".equals(data.getIsAnonymous()) ? "匿名用户" : data.getNickname())
                .setText(R.id.rv_assess_content, data.getInfo())
                .setText(R.id.rv_assess_time, data.getCeatedTime() + data.getAttr());
        RatingBarView ratingBar = holder.getView(R.id.rv_assess_ratingbar);
        ratingBar.setStar(data.getSppf(), false);
        ratingBar.setClickable(false);

        String pics = data.getPics();
        if (pics != null && !"".equals(pics)) {
            String[] split = pics.split(",");
            final List<String> list = Arrays.asList(split);
            RecyclerView rv = holder.getView(R.id.rv_assess_pic);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
            rv.setLayoutManager(gridLayoutManager);
            rv.addItemDecoration(new SpaceItemDecoration(0, 5, 0, 5));
            imageAdapter = new AssessImageAdapter(context, list, R.layout.rv_assess_rv_img);
            rv.setAdapter(imageAdapter);
            imageAdapter.setViewOnClickListener(new ViewOnClickListener() {
                @Override
                public void ViewOnClick(View view, int index) {
                    if (viewIndexClickListener != null) {
                        viewIndexClickListener.viewIndexClick(view, list, index);
                    }
                }
            });
        }
    }
}

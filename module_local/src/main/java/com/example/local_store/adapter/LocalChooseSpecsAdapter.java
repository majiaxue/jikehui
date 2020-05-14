package com.example.local_store.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.PopGuiGeBean;
import com.example.module_local.R;
import com.example.utils.OnChooseSpecsListener;
import com.example.utils.SpaceItemDecoration;

import java.util.List;

public class LocalChooseSpecsAdapter extends MyRecyclerAdapter<PopGuiGeBean> {
    private OnChooseSpecsListener listener;

    public LocalChooseSpecsAdapter(Context context, List<PopGuiGeBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public LocalChooseSpecsAdapter(Context context, List<PopGuiGeBean> mList, int mLayoutId, OnChooseSpecsListener listener) {
        super(context, mList, mLayoutId);
        this.listener = listener;
    }

    @Override
    public void convert(RecyclerViewHolder holder, PopGuiGeBean data, int position) {
        holder.setText(R.id.rv_pop_choose_specs_title, data.getTitle());
        RecyclerView rv = holder.getView(R.id.rv_pop_choose_specs_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new SpaceItemDecoration(0, (int) context.getResources().getDimension(R.dimen.dp_8), 0, (int) context.getResources().getDimension(R.dimen.dp_10)));

        LocalChooseSpecsInsideAdapter insideAdapter = new LocalChooseSpecsInsideAdapter(context, data.getContent(), R.layout.rv_pop_choose_specs_inside);
        rv.setAdapter(insideAdapter);
        listener.setOnChooseAdapter(insideAdapter,position);
    }
}

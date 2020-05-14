package com.example.classify.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.RightRecBean;
import com.example.module_home.R;

import java.util.List;

public class MyRightRecAdapter extends MyRecyclerAdapter<RightRecBean> {


    public MyRightRecAdapter(Context context, List mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RightRecBean data, int position) {
        holder.setText(R.id.classify_right_title, data.getName());
        List<RightRecBean.ListBean> list = data.getList();
        //嵌套recycler
        MyRightRecChildAdapter myRightChildAdapter = new MyRightRecChildAdapter(context, list, R.layout.item_rec_child);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
        RecyclerView classifyRightChild = holder.getView(R.id.classify_right_child);
        classifyRightChild.setLayoutManager(gridLayoutManager);
        classifyRightChild.setAdapter(myRightChildAdapter);

        myRightChildAdapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                ARouter.getInstance().build("/module_classify/ClassificationDetailsActivity").navigation();
            }
        });
    }

}

package com.example.user_classify.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.ClassifyBean;
import com.example.type_detail.TypeDetailActivity;
import com.example.user_store.R;

import java.util.List;

public class UserRightRecAdapter extends MyRecyclerAdapter<ClassifyBean.Records.RecordsSecond> {
    public UserRightRecAdapter(Context context, List<ClassifyBean.Records.RecordsSecond> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ClassifyBean.Records.RecordsSecond data, int position) {
        holder.setText(R.id.classify_right_title, data.getName());
        final List<ClassifyBean.Records.RecordsSecond.RecordsThird> list = data.getChildren();
        //嵌套recycler
        UserRightRecChildAdapter myRightChildAdapter = new UserRightRecChildAdapter(context, list, R.layout.item_rec_child);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
        RecyclerView classifyRightChild = holder.getView(R.id.classify_right_child);
        classifyRightChild.setLayoutManager(gridLayoutManager);
        classifyRightChild.setAdapter(myRightChildAdapter);

        myRightChildAdapter.setOnItemClick(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                ARouter.getInstance().build("/module_user_store/typeDetail").withString("categoryId", list.get(position).getId() + "").navigation();
            }
        });
    }
}

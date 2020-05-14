package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public abstract class MyRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    protected Context context;
    public List<T> mList;
    private int mLayoutId;
    private LayoutInflater inflater;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;
    public ViewOnClickListener viewOnClickListener;
    public ViewTwoOnClickListener viewTwoOnClickListener;
    public ViewThreeOnClickListener viewThreeOnClickListener;
    public OnFiveViewClickListener fiveViewClickListener;
    public OnTwoViewClickListener twoViewClickListener;
    public ViewFourOnClickListener viewFourOnClickListener;
    public OnViewIndexClickListener viewIndexClickListener;
    public OnPopChooseListener popChooseListener;
    public ViewThreeOnClickListener2 viewThreeOnClickListener2;
    private RecyclerView recyclerView;

    public MyRecyclerAdapter(Context context, List<T> mList, int mLayoutId) {
        this.context = context;
        this.mList = mList;
        this.mLayoutId = mLayoutId;
        inflater = LayoutInflater.from(context);
    }

    public MyRecyclerAdapter(Context context, List<T> mList) {
        this.context = context;
        this.mList = mList;
    }

    //在RecyclerView提供数据的时候调用
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(mLayoutId, parent, false);
        return RecyclerViewHolder.getInstance(context, view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && v != null) {
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(v);
                    listener.onItemClick(recyclerView, v, childAdapterPosition);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null && v != null) {
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(v);
                    longClickListener.onItemLongClick(recyclerView, v, childAdapterPosition);
                    return true;
                }
                return false;
            }
        });

        convert(holder, mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public abstract void convert(RecyclerViewHolder holder, T data, int position);

    /**
     * 以下为点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView parent, View view, int position);
    }

    public interface ViewOnClickListener {
        void ViewOnClick(View view, int index);
    }

    public interface ViewTwoOnClickListener {
        void ViewTwoOnClick(View view1, View view2, int position);
    }

    public interface ViewThreeOnClickListener {
        void ViewThreeOnClick(View view1, View view2, View view3, int position);
    }

    public interface ViewThreeOnClickListener2 extends ViewThreeOnClickListener {
        void viewThreeOnClick2(View view1, View view2, View view3, int position, int index);
    }

    public interface ViewFourOnClickListener {
        void ViewFourOnClick(View view1, View view2, View view3, View view4, int position);
    }

    public MyRecyclerAdapter setOnItemClick(OnItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    public void setOnItemLongClick(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public MyRecyclerAdapter setViewOnClickListener(ViewOnClickListener listener) {
        this.viewOnClickListener = listener;
        return this;
    }

    public void setViewTwoOnClickListener(ViewTwoOnClickListener listener) {
        this.viewTwoOnClickListener = listener;
    }

    public void setViewThreeOnClickListener(ViewThreeOnClickListener listener) {
        this.viewThreeOnClickListener = listener;
    }

    public void setViewThreeOnClickListener2(ViewThreeOnClickListener2 listener) {
        this.viewThreeOnClickListener2 = listener;
    }

    public void setViewFourOnClickListener(ViewFourOnClickListener listener) {
        this.viewFourOnClickListener = listener;

    }

    public interface OnFiveViewClickListener {
        void fiveViewClick(TextView zanCount, ImageView zanImg, TextView assessCount, ImageView assessImg, int groupPosition, ImageView img, int childPosition);
    }

    public void setOnFiveViewClickListener(OnFiveViewClickListener listener) {
        this.fiveViewClickListener = listener;
    }

    public interface OnTwoViewClickListener {
        void twoViewClick(View minus, View add, int outside, int inside);
    }

    public void setOnTwoViewClickListener(OnTwoViewClickListener listener) {
        this.twoViewClickListener = listener;
    }

    public interface OnViewIndexClickListener {
        void viewIndexClick(View view, List<String> list, int index);
    }

    public void setOnViewIndexClickListener(OnViewIndexClickListener listener) {
        this.viewIndexClickListener = listener;
    }

    public interface OnPopChooseListener {
        void onPopChoose(int parentPos, int childPos);
    }

    public void setOnPopChooseListener(OnPopChooseListener listener) {
        this.popChooseListener = listener;
    }

}

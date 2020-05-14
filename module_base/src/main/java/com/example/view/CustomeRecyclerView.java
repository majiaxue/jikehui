package com.example.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class CustomeRecyclerView extends RecyclerView {

    private Context mContext;

    public CustomeRecyclerView(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public CustomeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CustomeRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }
}

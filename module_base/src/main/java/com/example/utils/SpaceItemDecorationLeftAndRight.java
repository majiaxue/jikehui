package com.example.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.utils.DisplayUtil;

public class SpaceItemDecorationLeftAndRight extends RecyclerView.ItemDecoration {
    private int left;
    private int right;

    //leftRight为横向间的距离 topBottom为纵向间距离
    public SpaceItemDecorationLeftAndRight(int left, int right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) % 2 == 1) {
            outRect.right = right;
        } else if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.left = left;
        }
    }
}

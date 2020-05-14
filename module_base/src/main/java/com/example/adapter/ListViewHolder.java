package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ListViewHolder {
    private View mContentView;
    private SparseArray<View> mViews;
    private int mPosition;
    private Context context;

    private ListViewHolder(Context context, int layoutId, ViewGroup group, int position) {
        this.context = context;
        mPosition = position;
        mViews = new SparseArray<View>();
        mContentView = LayoutInflater.from(context).inflate(layoutId, group, false);
        mContentView.setTag(this);
    }

    public static ListViewHolder getInstance(Context context, View convertView, int layoutId, ViewGroup group, int position) {
        ListViewHolder holder = null;
        if (convertView == null) {
            holder = new ListViewHolder(context, layoutId, group, position);
        } else {
            holder = (ListViewHolder) convertView.getTag();
        }
        return holder;
    }

    public View getConvertView() {
        return mContentView;
    }

    public <T extends View> T getView(int resId) {
        View view = mViews.get(resId);
        if (null == view) {
            view = mContentView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    public ListViewHolder setText(int resId, String text) {
        TextView txt = getView(resId);
        txt.setText(text);
        return this;
    }

    public ListViewHolder setButtonText(int resId, String text) {
        Button button = getView(resId);
        button.setText(text);
        return this;
    }

    public ListViewHolder setTextColor(int resId, int colorId) {
        TextView txt = getView(resId);
        txt.setTextColor(colorId);
        return this;
    }

    public ListViewHolder setButtonTextColor(int resId, int colorId) {
        Button button = getView(resId);
        button.setTextColor(colorId);
        return this;
    }

    public ListViewHolder setImageResource(int resId, int drawableId) {
        ImageView imageView = getView(resId);
        imageView.setImageResource(drawableId);
        return this;
    }

    public ListViewHolder setImageBitmap(int resId, Bitmap bitmap) {
        ImageView imageView = getView(resId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public ListViewHolder setImageUrl(int resId, String url) {
        ImageView imageView = getView(resId);
        Glide.with(context).load(url).into(imageView);
        return this;
    }

    public ListViewHolder setBackground(int resId, int colorId) {
        View view = getView(resId);
        view.setBackgroundColor(colorId);
        return this;
    }

    public ListViewHolder setOnMyClickListener(int resId, final OnMyClickListener listener) {
        final View view = getView(resId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMyClick(view);
            }
        });
        return this;
    }

    public interface OnMyClickListener {
        void onMyClick(View view);
    }
}

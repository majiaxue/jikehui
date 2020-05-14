package com.example.buy2up.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

public abstract class BaseAdapter implements ListAdapter, SpinnerAdapter {
    public BaseAdapter() {
        throw new RuntimeException("Stub!");
    }

    public boolean hasStableIds() {
        throw new RuntimeException("Stub!");
    }

    public void registerDataSetObserver(DataSetObserver observer) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        throw new RuntimeException("Stub!");
    }

    public void notifyDataSetChanged() {
        throw new RuntimeException("Stub!");
    }

    public void notifyDataSetInvalidated() {
        throw new RuntimeException("Stub!");
    }

    public boolean areAllItemsEnabled() {
        throw new RuntimeException("Stub!");
    }

    public boolean isEnabled(int position) {
        throw new RuntimeException("Stub!");
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        throw new RuntimeException("Stub!");
    }

    public int getItemViewType(int position) {
        throw new RuntimeException("Stub!");
    }

    public int getViewTypeCount() {
        throw new RuntimeException("Stub!");
    }

    public boolean isEmpty() {
        throw new RuntimeException("Stub!");
    }
}

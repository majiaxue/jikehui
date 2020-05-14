package com.example.local_store.ShoppingRight;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.bean.LocalStoreBean;
import com.example.module_local.R;
import com.example.utils.LogUtil;

import java.util.List;

/**
 * Created by yadianna02 on 2018/7/31.
 */

public class SortAdapter extends RvAdapter<LocalStoreBean> {
    private int checkedPosition;

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }

    public SortAdapter(Context context, List<LocalStoreBean> list, RvListener listener, ShopOnClickListtener shopOnClickListtener) {
        super(context, list, listener, shopOnClickListtener);
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.shopping_left_rv;
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new SortHolder(view, viewType, listener);
    }

    private class SortHolder extends RvHolder<LocalStoreBean> {

        private TextView tvName;
        private View leftView;

        SortHolder(View itemView, int type, RvListener listener) {
            super(itemView, type, listener);
            tvName = (TextView) itemView.findViewById(R.id.shopping_left_rv_txt);
            leftView = itemView.findViewById(R.id.shopping_left_rv_view);
        }

        @Override
        public void bindHolder(LocalStoreBean localStoreBean, int position) {
            tvName.setText(localStoreBean.getShopCategoryName());
            if (position == checkedPosition) {
                tvName.setTextColor(Color.parseColor("#fb5318"));
                leftView.setVisibility(View.VISIBLE);
            } else {
                tvName.setTextColor(Color.parseColor("#333333"));
                leftView.setVisibility(View.GONE);
            }
        }

    }
}

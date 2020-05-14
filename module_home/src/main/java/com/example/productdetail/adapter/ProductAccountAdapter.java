package com.example.productdetail.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.ProductCenterBean;
import com.example.module_home.R;

import java.util.List;

public class ProductAccountAdapter extends MyRecyclerAdapter<ProductCenterBean.RecordsBean> {
    public ProductAccountAdapter(Context context, List<ProductCenterBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, ProductCenterBean.RecordsBean data, int position) {
        holder.setText(R.id.rv_product_detail_name, data.getTestName() + "：")
                .setText(R.id.rv_product_detail_download, data.getTestAddress())
                .setText(R.id.rv_product_detail_account, data.getTestAccount())
                .setText(R.id.rv_product_detail_password, data.getTestPassword());
    }
}

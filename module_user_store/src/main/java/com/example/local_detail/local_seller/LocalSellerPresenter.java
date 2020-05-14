package com.example.local_detail.local_seller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.LocalShopBean;
import com.example.local_detail.adapter.SellerImaAdapter;
import com.example.mvp.BasePresenter;
import com.example.user_store.R;
import com.example.utils.PopUtils;

import java.util.ArrayList;
import java.util.List;

public class LocalSellerPresenter extends BasePresenter<LocalSellerView> {
    private List<String> imgList = new ArrayList<>();
    private SellerImaAdapter imgAdapter;

    public LocalSellerPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(String pics) {
        if (pics != null) {
            String[] split = pics.split(",");
            for (int i = 0; i < split.length; i++) {
                imgList.add(split[i]);
            }
        }
        imgAdapter = new SellerImaAdapter(mContext, imgList, R.layout.rv_local_seller_img);
        if (getView() != null) {
            getView().loadImg(imgAdapter);
        }

        imgAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                PopUtils.popAssessBigPic(mContext, imgList, position);
            }
        });
    }
}

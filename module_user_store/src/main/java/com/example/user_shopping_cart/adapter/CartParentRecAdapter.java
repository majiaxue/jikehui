package com.example.user_shopping_cart.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.CartBean;
import com.example.user_store.R;
import com.example.utils.OnCountChangeListener;
import com.example.utils.OnSelectViewListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class CartParentRecAdapter extends MyRecyclerAdapter<CartBean.RecordsBean> {

    private ImageView cartParentCheck;
    private boolean allCheck = true;
    private List<CartBean.RecordsBean> parentList;
    private List<CartBean.RecordsBean.ItemsBean> itemsBeanList = new ArrayList<>();
    private CartChildRecAdapter cartChildRecAdapter;
    private OnSelectViewListener listener;
    private OnCountChangeListener changeListener;

    public CartParentRecAdapter(Context context, List<CartBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
        parentList = mList;
    }

    public CartParentRecAdapter(Context context, List<CartBean.RecordsBean> mList, int mLayoutId, OnSelectViewListener listener, OnCountChangeListener changeListener) {
        super(context, mList, mLayoutId);
        parentList = mList;
        this.listener = listener;
        this.changeListener = changeListener;
    }

    @Override
    public void convert(RecyclerViewHolder holder, final CartBean.RecordsBean data, final int position) {
        itemsBeanList.addAll(data.getItems());
        holder.setText(R.id.cart_parent_name, data.getSellerName());
        cartParentCheck = holder.getView(R.id.cart_parent_check);

        if (data.isCheck()) {
            cartParentCheck.setImageResource(R.drawable.icon_xuanzhong);
        } else {
            cartParentCheck.setImageResource(R.drawable.icon_weixuanzhong);
        }

        viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.cart_parent_check), holder.getView(R.id.cart_parent_go_shop), position);

        RecyclerView cartParentRec = holder.getView(R.id.cart_parent_rec);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        cartParentRec.setLayoutManager(linearLayoutManager);
        cartChildRecAdapter = new CartChildRecAdapter(context, data.getItems(), R.layout.item_cart_child);
        cartParentRec.setAdapter(cartChildRecAdapter);

        cartChildRecAdapter.setViewOnClickListener(new ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        allCheck = true;
                        isAllCheck(position, index);
                    }
                });
            }
        });

        cartChildRecAdapter.setOnCartListChangeListener(new CartChildRecAdapter.OnCartListChangeListener() {
            @Override
            public void onProductNumberChange(int childPosition, int number) {
                changeListener.setOnCountChangedListener(position, childPosition, number);
            }
        });

//        cartChildRecAdapter.setViewTwoOnClickListener(new ViewTwoOnClickListener() {
//            @Override
//            public void ViewTwoOnClick(View view1, View view2, final int childPosition) {
//                view1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        allCheck = true;
//                        isAllCheck(position, childPosition);
//                    }
//                });
//
//                view2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//
//
//            }
//        });
    }

    public void setEdit(boolean isEdit) {
        cartChildRecAdapter.setIsEdit(isEdit);
    }

    //点击选中商品
    private void isAllCheck(int position, int childPosition) {
        if (parentList.get(position).getItems().get(childPosition).getChecked() == 0) {
            parentList.get(position).getItems().get(childPosition).setChecked(1);
        } else {
            parentList.get(position).getItems().get(childPosition).setChecked(0);
        }

//        browsingHistoryChildAdapter.notifyDataSetChanged();

        for (int i = 0; i < parentList.get(position).getItems().size(); i++) {
            if (parentList.get(position).getItems().get(i).getChecked() == 1) {
                allCheck = false;
            }
        }

        parentList.get(position).setCheck(allCheck);
        boolean isAllCheck = true;
        for (int i = 0; i < parentList.size(); i++) {
            if (!parentList.get(i).isCheck()) {
                isAllCheck = false;
                break;
            }
        }
        listener.setOnSelectViewListener(isAllCheck, position, childPosition);
        notifyDataSetChanged();
    }

    //当商家选中时商品全选
    public void checkAll(int position, boolean status) {
        if (status) {
            for (int i = 0; i < parentList.get(position).getItems().size(); i++) {
                parentList.get(position).getItems().get(i).setChecked(1);
            }
        } else {
            for (int i = 0; i < parentList.get(position).getItems().size(); i++) {
                parentList.get(position).getItems().get(i).setChecked(0);
            }
        }

        cartChildRecAdapter.notifyDataSetChanged();
    }

}

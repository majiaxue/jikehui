package com.example.user_shopping_cart.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.CartBean;
import com.example.user_store.R;
import com.example.view.AddAndSubView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class CartChildRecAdapter extends MyRecyclerAdapter<CartBean.RecordsBean.ItemsBean> {

    private ImageView cartChildCheck;
    public boolean isEdit = false;

    public CartChildRecAdapter(Context context, List<CartBean.RecordsBean.ItemsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, final CartBean.RecordsBean.ItemsBean data, final int position) {
        cartChildCheck = holder.getView(R.id.cart_child_check);
        if (data.getChecked() == 0) {
            //选中
            cartChildCheck.setImageResource(R.drawable.icon_xuanzhong);
        } else {
            //未选中
            cartChildCheck.setImageResource(R.drawable.icon_weixuanzhong);
        }


        holder.setImageFresco(R.id.cart_child_image, data.getProductPic());
        holder.setText(R.id.cart_child_name, data.getProductName());
        holder.setText(R.id.cart_child_colour, data.getSp1() + "，");
        holder.setText(R.id.cart_child_size, data.getSp2());
        holder.setText(R.id.cart_child_price, "￥" + data.getPrice());
        final AddAndSubView addAndSub = holder.getView(R.id.cart_child_add_and_sub);
        addAndSub.setNumber(data.getQuantity());
        addAndSub.setOnNumberChangeListener(new AddAndSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int num) {
                onCartListChangeListener.onProductNumberChange(position, addAndSub.getNumber());
            }
        });

        if (isEdit) {
            addAndSub.setVisibility(View.GONE);
        } else {
            addAndSub.setVisibility(View.VISIBLE);
        }
//        viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.cart_child_check), holder.getView(R.id.cart_child_add_and_sub), position);
        viewOnClickListener.ViewOnClick(holder.getView(R.id.cart_child_check), position);
    }

    public void setIsEdit(boolean boo) {
        isEdit = boo;
        notifyDataSetChanged();
    }

    private OnCartListChangeListener onCartListChangeListener;

    public void setOnCartListChangeListener(OnCartListChangeListener onCartListChangeListener) {
        this.onCartListChangeListener = onCartListChangeListener;
    }

    public interface OnCartListChangeListener {

        //加减器接口回调
        void onProductNumberChange(int childPosition, int number);

    }
}

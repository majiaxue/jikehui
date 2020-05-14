package com.example.mineorder.stayappraise.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.MineOrderBean;
import com.example.module_user_mine.R;
import com.example.utils.ArithUtil;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/30
 * Describe:
 */
public class StayAppraiseChildAdapter extends MyRecyclerAdapter<MineOrderBean.OrderListBean.OrderItemsBean> {

    private String payAmount;

    public StayAppraiseChildAdapter(Context context, List<MineOrderBean.OrderListBean.OrderItemsBean> mList, int mLayoutId, String payAmount) {
        super(context, mList, mLayoutId);
        this.payAmount = payAmount;
    }

    @Override
    public void convert(RecyclerViewHolder holder, MineOrderBean.OrderListBean.OrderItemsBean data, int position) {
        TextView btnRight = holder.getView(R.id.stay_appraise_child_btn_right);
        TextView btnLeft = holder.getView(R.id.stay_appraise_child_btn_left);
        if (1 == data.getIsComment()) {
            btnLeft.setText("已评价");
            btnRight.setText("再次购买");
        } else {
            btnLeft.setText("再次购买");
            btnRight.setText("立即评价");
        }

        holder.setImageFresco(R.id.stay_appraise_child_img, data.getProductPic());
        holder.setText(R.id.stay_appraise_child_name, data.getProductName());
        holder.setText(R.id.stay_appraise_child_message, data.getProductAttr());
        holder.setText(R.id.stay_appraise_child_price, "￥" + data.getProductPrice());
        holder.setText(R.id.stay_appraise_child_count, "X" + data.getProductQuantity());

        holder.setText(R.id.stay_appraise_child_total, "共" + data.getProductQuantity() + "件商品  合计：￥" + payAmount);

        viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.stay_appraise_child_btn_left), holder.getView(R.id.stay_appraise_child_btn_right), position);

    }
}

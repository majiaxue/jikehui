package com.example.browsinghistory.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.BrowsingHistoryBean;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class BrowsingHistoryChildAdapter extends MyRecyclerAdapter<BrowsingHistoryBean.RecordsBean.ItemBean> {

    private boolean isChildCompile;
    private ImageView childCheck;
    private List<BrowsingHistoryBean.RecordsBean.ItemBean> data;

    public BrowsingHistoryChildAdapter(Context context, List<BrowsingHistoryBean.RecordsBean.ItemBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public BrowsingHistoryChildAdapter(Context context, List<BrowsingHistoryBean.RecordsBean.ItemBean> mList, int mLayoutId, boolean childCompile) {
        super(context, mList, mLayoutId);
        this.isChildCompile = childCompile;
        this.data = mList;
    }

    @Override
    public void convert(RecyclerViewHolder holder, BrowsingHistoryBean.RecordsBean.ItemBean data, int position) {
        childCheck = holder.getView(R.id.browsing_history_child_check);

        if (isChildCompile) {
            childCheck.setVisibility(View.VISIBLE);
        } else {
            childCheck.setVisibility(View.GONE);
        }

        if (data.isCheck()) {
            childCheck.setImageResource(R.drawable.icon_xuanzhong);
        } else {
            childCheck.setImageResource(R.drawable.icon_weixuanzhong);
        }

        holder.setImageFresco(R.id.browsing_history_child_image, data.getPic());
        holder.setText(R.id.browsing_history_child_name, data.getName());
        holder.setText(R.id.browsing_history_child_price, "￥" + data.getPrice());
        holder.setText(R.id.browsing_history_child_payment_amount, data.getSale() + "人付款");
        holder.setText(R.id.browsing_history_child_good_reputation, data.getGoodReputation() + "好评");
        holder.setText(R.id.browsing_history_child_shop, data.getSellerName());
        viewTwoOnClickListener.ViewTwoOnClick(holder.getView(R.id.browsing_history_child_check), holder.getView(R.id.browsing_history_child_look_similar), position);
    }

    public void setChildCompile(boolean childCompile) {
        isChildCompile = childCompile;
    }


}

package com.example.upgrade.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.OperatorBean;
import com.example.module_mine.R;
import com.example.utils.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class UpgradeAdapter extends MyRecyclerAdapter<OperatorBean> {
    public UpgradeAdapter(Context context, List<OperatorBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, OperatorBean data, int position) {

        holder.setText(R.id.rv_upgrade_title, data.getName());
        holder.setBackgroundResource(R.id.rv_upgrade_rela, "1".equals(data.getId()) ? R.drawable.tongpaihuiyuan : "2".equals(data.getId()) ? R.drawable.yinpaihuiyuan : R.drawable.huangjinhuiyuan);


        if ("0".equals(data.getUpType())) {
            holder.getView(R.id.rv_upgrade_factor1).setVisibility(View.GONE);
            holder.setText(R.id.rv_upgrade_txt, "条件一：")
                    .setTextFormHtml(R.id.rv_upgrade_price, "支付<font color='#e20707>'￥" + data.getPrice() + "</font>即可升级");
        } else if ("1".equals(data.getUpType())) {
            holder.getView(R.id.rv_upgrade_factor2).setVisibility(View.GONE);
        } else if ("2".equals(data.getUpType())) {
            holder.setTextFormHtml(R.id.rv_upgrade_price, "支付<font color='#e20707>'￥" + data.getPrice() + "</font>即可升级");
        }

        if (!"0".equals(data.getUpType())) {
            List<String> factor = getFactor(data);
            RecyclerView rv = holder.getView(R.id.rv_upgrade_content);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            rv.setLayoutManager(gridLayoutManager);
            rv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) context.getResources().getDimension(R.dimen.dp_5)));
            UpgradeInsideAdapter insideAdapter = new UpgradeInsideAdapter(context, factor, R.layout.rv_upgrade_inside_rv);
            rv.setAdapter(insideAdapter);
        }

        if (viewThreeOnClickListener != null) {
            viewThreeOnClickListener.ViewThreeOnClick(holder.getView(R.id.rv_upgrade_btn), holder.getView(R.id.rv_upgrade_topay), holder.getView(R.id.rv_upgrade_description), position);
        }
    }

    private List<String> getFactor(OperatorBean data) {
//        StringBuffer str = new StringBuffer();
        List<String> list = new ArrayList<>();
        if (data.getDirectFansNum() != null) {
//            str.append("直推有效粉丝" + data.getDirectFansNum() + "人\n");
            list.add("直推有效粉丝:<font color='#e20707'>" + data.getDirectFansNum() + "</font>人");
        }
        if (data.getIndirectFansNum() != null) {
//            str.append("非直推有效粉丝" + data.getIndirectFansNum() + "人\n");
            list.add("非直推有效粉丝:<font color='#e20707'>" + data.getIndirectFansNum() + "</font>人");
        }
        if (data.getSelfOrderNum() != null) {
//            str.append("个人自购结算订单" + data.getSelfOrderNum() + "单\n");
            list.add("个人自购结算订单:<font color='#e20707'>" + data.getSelfOrderNum() + "</font>单");
        }
        if (data.getSelfCommission() != null) {
//            str.append("个人累计获得佣金" + data.getSelfCommission() + "元\n");
            list.add("个人累计获得佣金:<font color='#e20707'>" + data.getSelfCommission() + "</font>元");
        }
        if (data.getRecommendNum() != null) {
//            str.append("推荐运营商" + data.getRecommendNum() + "个\n");
            list.add("推荐运营商:<font color='#e20707'>" + data.getRecommendNum() + "</font>个");
        }
        return list;
    }
}

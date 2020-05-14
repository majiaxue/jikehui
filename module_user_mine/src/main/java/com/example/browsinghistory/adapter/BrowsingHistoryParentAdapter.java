package com.example.browsinghistory.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.BrowsingHistoryBean;
import com.example.module_user_mine.R;
import com.example.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class BrowsingHistoryParentAdapter extends MyRecyclerAdapter<BrowsingHistoryBean.RecordsBean> {

    private boolean isParentCompile;
    private BrowsingHistoryChildAdapter browsingHistoryChildAdapter;
    private List<BrowsingHistoryBean.RecordsBean> parentList;
    private boolean allCheck = true;
    private ImageView parentCheck;
    private List<BrowsingHistoryBean.RecordsBean.ItemBean> childList = new ArrayList<>();


    public BrowsingHistoryParentAdapter(Context context, List<BrowsingHistoryBean.RecordsBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public BrowsingHistoryParentAdapter(Context context, List<BrowsingHistoryBean.RecordsBean> mList, int mLayoutId, boolean parentCompile) {
        super(context, mList, mLayoutId);
        this.isParentCompile = parentCompile;
        parentList = mList;
    }

    @Override
    public void convert(RecyclerViewHolder holder, final BrowsingHistoryBean.RecordsBean data, final int position) {
        childList.addAll(data.getItem());
        String createTime = data.getCreateTime();
        String[] s = createTime.split(" ");
        String s1 = s[0];
        String[] split = s1.split("-");

        holder.setText(R.id.browsing_history_parent_time, split[1] + "月" + split[2] + "日");

        LogUtil.e("simpleDateFormat------------->" + split[1] + "月" + split[2] + "日");


        parentCheck = holder.getView(R.id.browsing_history_parent_check);
        if (isParentCompile) {
            parentCheck.setVisibility(View.VISIBLE);
        } else {
            parentCheck.setVisibility(View.GONE);
        }

        if (data.isCheck()) {
        parentCheck.setImageResource(R.drawable.icon_xuanzhong);
        } else {
        parentCheck.setImageResource(R.drawable.icon_weixuanzhong);
        }

        viewOnClickListener.ViewOnClick(holder.getView(R.id.browsing_history_parent_check), position);

        final RecyclerView browsingHistoryParentRec = holder.getView(R.id.browsing_history_parent_rec);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        browsingHistoryParentRec.setLayoutManager(linearLayoutManager);
        browsingHistoryChildAdapter = new BrowsingHistoryChildAdapter(context, data.getItem(), R.layout.item_browsing_history_child, false);
        browsingHistoryChildAdapter.setChildCompile(isParentCompile);
        browsingHistoryParentRec.setAdapter(browsingHistoryChildAdapter);
        browsingHistoryChildAdapter.setViewTwoOnClickListener(new ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(final View view1, View view2, final int childPosition) {
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //选中
                        allCheck = true;
                        isAllCheck(position, childPosition);
                    }
                });

                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(context, "childPosition:" + childPosition, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }


    public void isAllCheck(int position, int childPosition) {

        Log.d("77777", "position: --->" + position);
        Log.d("77777", "childPosition: ----->" + childPosition);

        if (parentList.get(position).getItem().get(childPosition).isCheck()) {
            parentList.get(position).getItem().get(childPosition).setCheck(false);
        } else {
            parentList.get(position).getItem().get(childPosition).setCheck(true);
        }

//        browsingHistoryChildAdapter.notifyDataSetChanged();

        for (int i = 0; i < parentList.get(position).getItem().size(); i++) {
            if (!parentList.get(position).getItem().get(i).isCheck()) {
                allCheck = false;
            }
        }

        parentList.get(position).setCheck(allCheck);

        notifyDataSetChanged();
    }


    public void checkAll(int position, boolean status) {
        if (status) {
            for (int i = 0; i < parentList.get(position).getItem().size(); i++) {
                parentList.get(position).getItem().get(i).setCheck(false);
            }
        } else {
            for (int i = 0; i < parentList.get(position).getItem().size(); i++) {
                parentList.get(position).getItem().get(i).setCheck(true);
            }
        }

        browsingHistoryChildAdapter.notifyDataSetChanged();
    }


    public void setCompile(boolean compile) {
        isParentCompile = compile;
        notifyDataSetChanged();
    }
}

package com.example.user_classify.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.entity.LeftChildBean;
import com.example.entity.LeftGroupBean;
import com.example.user_store.R;
import com.example.user_store.R2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<LeftGroupBean> groupList;
    private List<List<LeftChildBean>> childList;

    public UserExpandableAdapter(Context context, List<LeftGroupBean> groupList, List<List<LeftChildBean>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupViewHolder groupViewHolder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.group, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        //给group赋值
        groupViewHolder.groupName.setText(groupList.get(groupPosition).getName());
        boolean selected = groupList.get(groupPosition).isSelected();
        if (groupPosition != 0) {
            if (selected) {
                //选中状态
                groupViewHolder.groupSelectBg.setVisibility(View.VISIBLE);
                groupViewHolder.groupName.setTextColor(Color.parseColor("#fc5917"));
                groupViewHolder.groupBg.setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                //未选中状态
                groupViewHolder.groupSelectBg.setVisibility(View.INVISIBLE);
                groupViewHolder.groupName.setTextColor(Color.parseColor("#666666"));
                groupViewHolder.groupBg.setBackgroundColor(Color.parseColor("#f5f5f5"));
            }
        } else {
            if (selected) {
                //选中状态
                groupViewHolder.groupSelectBg.setVisibility(View.VISIBLE);
                groupViewHolder.groupName.setTextColor(Color.parseColor("#fc5917"));
                groupViewHolder.groupBg.setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                //未选中状态
                groupViewHolder.groupSelectBg.setVisibility(View.INVISIBLE);
                groupViewHolder.groupName.setTextColor(Color.parseColor("#666666"));
                groupViewHolder.groupBg.setBackgroundColor(Color.parseColor("#f5f5f5"));
            }
        }


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder childViewHolder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.child, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        //给child赋值
        childViewHolder.childName.setText(childList.get(groupPosition).get(childPosition).getName());

        boolean selected = childList.get(groupPosition).get(childPosition).isSelected();
        if (selected) {
            childViewHolder.childName.setTextColor(Color.parseColor("#fc5917"));
        } else {
            childViewHolder.childName.setTextColor(Color.parseColor("#666666"));
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class GroupViewHolder {
        @BindView(R2.id.group_select_bg)
        TextView groupSelectBg;
        @BindView(R2.id.group_name)
        TextView groupName;
        @BindView(R2.id.group_bg)
        LinearLayout groupBg;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildViewHolder {
        @BindView(R2.id.child_name)
        TextView childName;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

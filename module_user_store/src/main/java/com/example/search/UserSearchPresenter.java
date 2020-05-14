package com.example.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.CommonResource;
import com.example.dbflow.DBflowUtil;
import com.example.dbflow.SearchHistoryBean;
import com.example.mvp.BasePresenter;
import com.example.user_store.R;
import com.example.view.FlowLayout;

import java.util.List;

public class UserSearchPresenter extends BasePresenter<UserSearchView> {
    private TextView searchTextView;

    public UserSearchPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void searchEdit(String string, String from) {
        if (CommonResource.HISTORY_LOCAL.equals(from)) {
            if (string != null && !"".equals(string.trim())) {
                DBflowUtil.getInstance().insert(string, CommonResource.HISTORY_LOCAL);
            }
            ARouter.getInstance().build("/user/localList").withString("search", string).navigation();
        } else {
            if (string != null && !"".equals(string.trim())) {
                DBflowUtil.getInstance().insert(string, CommonResource.HISTORY_USER);
            }
            ARouter.getInstance().build("/module_user_store/typeDetail").withString("search", string).navigation();
        }
    }

    public void getHistory(FlowLayout searchFlowLayout, final String from) {
        if (CommonResource.HISTORY_LOCAL.equals(from)) {
            final List<SearchHistoryBean> list = DBflowUtil.getInstance().query(CommonResource.HISTORY_LOCAL);
            for (int i = 0; i < list.size(); i++) {
                searchTextView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.search_text_view, searchFlowLayout, false);
                searchTextView.setText(list.get(i).getContent());
                final int finalI = i;
                searchTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchEdit(list.get(finalI).getContent(), from);
                    }
                });
                searchFlowLayout.addView(searchTextView);
            }
        } else {
            final List<SearchHistoryBean> list = DBflowUtil.getInstance().query(CommonResource.HISTORY_USER);
            for (int i = 0; i < list.size(); i++) {
                searchTextView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.search_text_view, searchFlowLayout, false);
                searchTextView.setText(list.get(i).getContent());
                final int finalI = i;
                searchTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchEdit(list.get(finalI).getContent(), from);
                    }
                });
                searchFlowLayout.addView(searchTextView);
            }
        }
    }
}

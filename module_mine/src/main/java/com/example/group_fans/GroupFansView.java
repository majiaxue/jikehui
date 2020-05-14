package com.example.group_fans;

import com.example.bean.GroupFansPeopleBean;
import com.example.group_fans.adapter.GroupFansRvAdapter;
import com.example.mvp.IView;

public interface GroupFansView extends IView {
    void loadUI(int totalPage, int totalFans);

    void loadCount(GroupFansPeopleBean peopleBean);

    void loadFinish();

    void noFans();

    void loadRv(GroupFansRvAdapter adapter);
}

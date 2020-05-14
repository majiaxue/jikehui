package com.example.universallist;

import com.example.mvp.IView;
import com.example.universallist.adapter.BaoYouAdapter;
import com.example.universallist.adapter.HotRecommendRecAdapter;
import com.example.universallist.adapter.UniversalListRecAdapter;

public interface UniversalListView extends IView {
    void finishRefresh();

    void loadData(BaoYouAdapter baoYouAdapter);

    void loadData(HotRecommendRecAdapter hotRecommendRecAdapter);

    void loadData(UniversalListRecAdapter universalListRecAdapter);
}

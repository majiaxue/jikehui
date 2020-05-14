package com.example.user_classify;

import com.example.bean.BannerBean;
import com.example.mvp.IView;
import com.example.user_classify.adapter.UserLeftRvAdapter;
import com.example.user_classify.adapter.UserRightRecAdapter;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public interface ClassifyView extends IView {

    void loadRv(UserLeftRvAdapter leftAdapter, UserRightRecAdapter rightAdapter);

    void loadBanner(List<BannerBean.RecordsBean> list);

}

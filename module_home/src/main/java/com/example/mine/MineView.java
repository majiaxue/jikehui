package com.example.mine;

import com.example.bean.HomePredictBean;
import com.example.bean.UserInfoBean;
import com.example.mine.adapter.MyToolAdapter;
import com.example.mvp.IView;

public interface MineView extends IView {
    void loadMyTool(MyToolAdapter adapter);

    void loginSuccess(UserInfoBean userInfo);

    void onError();

    void loadPredict(HomePredictBean homePredictBean);
}

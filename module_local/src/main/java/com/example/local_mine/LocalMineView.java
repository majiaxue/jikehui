package com.example.local_mine;

import com.example.bean.RedPackageBean;
import com.example.mvp.IView;

import java.util.List;

public interface LocalMineView extends IView {
    void loadBanner(List<RedPackageBean> redPackageBeans);

    void callBack();

}

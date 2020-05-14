package com.example.user_mine;

import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public interface MineView extends IView {
    void browsingHistoryCount(int count);

    void shopCollectCount(int count);

    void goodsCollectionCount(int count);

    void daifahuo(int count);

    void daishouhuo(int count);

    void daipingjia(int count);

    void daifukuan(int count);

}

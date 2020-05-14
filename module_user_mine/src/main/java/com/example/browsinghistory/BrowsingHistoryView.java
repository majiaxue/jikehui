package com.example.browsinghistory;

import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public interface BrowsingHistoryView extends IView {
    void isCompile(boolean isCompile);

    void isCheckAll(boolean isCheckAll);

    void empty(boolean isEmpty);
}

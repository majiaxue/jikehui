package com.example.secondarydetails;

import com.example.adapter.SecondaryJDRecAdapter;
import com.example.mvp.IView;
import com.example.adapter.SecondaryPddRecAdapter;
import com.example.secondarydetails.adapter.SecondaryTBRecAdapter;

/**
 * Created by cuihaohao on 2019/5/31
 * Describe:
 */
public interface SecondaryDetailsView extends IView {
    void lodeRec(SecondaryPddRecAdapter baseRecAdapter);

    void lodeTBRec(SecondaryTBRecAdapter secondaryTBRecAdapter);

    void lodeJDRec(SecondaryJDRecAdapter secondaryJDRecAdapter);

    void noGoods(boolean isNoGoods);
}

package com.example.classificationdetails;

import com.example.adapter.BaseRecAdapter;
import com.example.adapter.SecondaryJDRecAdapter;
import com.example.adapter.SecondaryPddRecAdapter;
import com.example.classificationdetails.adapter.ClassificationRecAdapter;
import com.example.classificationdetails.adapter.JdWaterfallAdapter;
import com.example.classificationdetails.adapter.PddWaterAdapter;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/20
 * Describe:
 */
public interface ClassificationDetailsView extends IView {
    void loadTBLstRv(BaseRecAdapter adapter);

    void loadTBWaterfallRv(ClassificationRecAdapter adapter);

    void loadPDDLstRv(SecondaryPddRecAdapter adapter);

    void loadPDDWaterfallRv(PddWaterAdapter adapter);

    void loadJDLstRv(SecondaryJDRecAdapter adapter);

    void loadJDWaterfallRv(JdWaterfallAdapter adapter);

    void loadFinish();

    void updateTitle(boolean isPositiveSalesVolume, boolean isPositivePrice, boolean isPositiveCredit);

    void moveTo(int num, boolean isWaterfall);
}

package com.example.logisticsinformation;

import com.example.bean.LogisticsInforMationBean;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/5/28
 * Describe:
 */
public interface LogisticsInformationView extends IView {
    void traces(LogisticsInforMationBean inforMationBeanList,int size);
}

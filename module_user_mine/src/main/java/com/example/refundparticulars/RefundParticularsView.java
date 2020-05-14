package com.example.refundparticulars;

import com.example.bean.AlterationBean;
import com.example.bean.RefundParticularsBean;
import com.example.mvp.IView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public interface RefundParticularsView extends IView {
    void initView(RefundParticularsBean refundParticularsBean);
}

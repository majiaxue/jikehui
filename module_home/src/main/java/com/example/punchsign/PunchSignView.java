package com.example.punchsign;

import com.example.bean.PunchSignBean;
import com.example.mvp.IView;

public interface PunchSignView extends IView {
    void punchSign(PunchSignBean punchSignBean);

    void qianDao();

    void meiRiQianDao(int type);

    void shareCount(int count);

    void yaoQingHaoYou();

    void firstOrder();

    void order();

    void fans();
}

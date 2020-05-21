package com.example.hehuorenfans;

import com.example.bean.PartnerBean;
import com.example.hehuorenfans.adapter.PartnerAdapter;
import com.example.mvp.IView;

public interface PartnerView extends IView {
    void loadPartner(PartnerBean partnerBean);

    void loadFinish();

    void loadAdapter(PartnerAdapter adapter);
}

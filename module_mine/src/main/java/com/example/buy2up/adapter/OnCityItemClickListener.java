package com.example.buy2up.adapter;

import com.example.bean.City2Bean;
import com.example.bean.DistrictBean;
import com.example.bean.ProvinceBean;

public abstract class OnCityItemClickListener {

    /**
     * 当选择省市区三级选择器时，需要覆盖此方法
     * @param province
     * @param city
     * @param district
     */
    public void onSelected(ProvinceBean province, City2Bean city, DistrictBean district) {

    }

    /**
     * 取消
     */
    public void onCancel() {

    }
}

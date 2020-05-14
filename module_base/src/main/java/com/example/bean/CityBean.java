package com.example.bean;

import com.example.view.addressselect.CityInterface;

/**
 * Created by cuihaohao on 2019/6/13
 * Describe:
 */
public class CityBean implements CityInterface {
    /**
     * id : 2
     * areaParentCode : 1
     * areaName : 北京市
     * areaType : 1
     */

    private int id;
    private String areaParentCode;
    private String areaName;
    private int areaType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaParentCode() {
        return areaParentCode;
    }

    public void setAreaParentCode(String areaParentCode) {
        this.areaParentCode = areaParentCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getAreaType() {
        return areaType;
    }

    public void setAreaType(int areaType) {
        this.areaType = areaType;
    }

    @Override
    public String getCityName() {
        return areaName;
    }

    @Override
    public int getCityId() {
        return id;
    }
}

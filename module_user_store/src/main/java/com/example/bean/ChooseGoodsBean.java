package com.example.bean;

import java.util.List;

public class ChooseGoodsBean {
    private String type;
    private List<ChooseInsideBean> list;

    public ChooseGoodsBean(String type, List<ChooseInsideBean> list) {
        this.type = type;
        this.list = list;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ChooseInsideBean> getList() {
        return list;
    }

    public void setList(List<ChooseInsideBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ChooseGoodsBean{" +
                "type='" + type + '\'' +
                ", list=" + list +
                '}';
    }
}

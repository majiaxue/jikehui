package com.example.entity;

/**
 * Created by cuihaohao on 2019/5/20
 * Describe:
 */
public class LeftChildBean {
    private String name;
    private boolean isSelected = false;

    public LeftChildBean() {
    }

    public LeftChildBean(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

package com.example.entity;

/**
 * Created by cuihaohao on 2019/5/20
 * Describe:
 */
public class LeftGroupBean {
    private String name;
    private boolean isSelected = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LeftGroupBean(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

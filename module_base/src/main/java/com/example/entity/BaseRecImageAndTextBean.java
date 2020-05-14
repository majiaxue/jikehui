package com.example.entity;

/**
 * Created by cuihaohao on 2019/5/15
 * Describe:
 */
public class BaseRecImageAndTextBean {

    private String name;
    private int image;

    public BaseRecImageAndTextBean(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

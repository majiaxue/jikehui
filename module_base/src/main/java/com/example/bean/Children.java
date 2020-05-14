package com.example.bean;

import java.util.List;

public class Children<T> {
    private List<T> children;

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Children{" +
                "children=" + children +
                '}';
    }
}

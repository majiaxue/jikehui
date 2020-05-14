package com.example.bean;

import java.io.Serializable;
import java.util.List;

public class Records<T> implements Serializable {
    private List<T> Records;

    public List<T> getRecords() {
        return Records;
    }

    public void setRecords(List<T> records) {
        Records = records;
    }

    @Override
    public String toString() {
        return "Records{" +
                "Records=" + Records +
                '}';
    }
}

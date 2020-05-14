package com.example.bean;

import java.util.List;

public class BaseEntityList<T> {
    private String code;
    private List<T> data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseEntityList{" +
                "code='" + code + '\'' +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}

package com.example.bean;

public class ProductLiuYanBean {
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 内容
     */
    private String message;

    public ProductLiuYanBean(String name, String phone, String message) {
        this.name = name;
        this.phone = phone;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ProductLiuYanBean{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

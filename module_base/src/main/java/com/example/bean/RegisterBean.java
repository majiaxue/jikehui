package com.example.bean;

public class RegisterBean {
    private String phone;
    private String checkCode;
    private String password;
    private String inviteCode;

    public RegisterBean(String phone, String checkCode, String password) {
        this.phone = phone;
        this.checkCode = checkCode;
        this.password = password;
    }

    public RegisterBean(String phone, String checkCode, String password, String inviteCode) {
        this.phone = phone;
        this.checkCode = checkCode;
        this.password = password;
        this.inviteCode = inviteCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    @Override
    public String toString() {
        return "RegisterBean{" +
                "phone='" + phone + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", password='" + password + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                '}';
    }
}

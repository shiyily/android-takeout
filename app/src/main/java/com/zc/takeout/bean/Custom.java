package com.zc.takeout.bean;

public class Custom {
    private String account;
    private String tel;
    private String password;

    public Custom() {
    }

    public Custom(String accout, String tel, String password) {
        this.account = accout;
        this.tel = tel;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "account='" + account + '\'' +
                ", tel='" + tel + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

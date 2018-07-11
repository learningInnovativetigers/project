package com.tensquare.base.controller;

/**
 * Title: User
 * Description: 用户实体类
 * author: wenbiao
 * create: 2018-07-04 01:34
 */
public class User {
    private String name;
    private String password;
    private String Address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}

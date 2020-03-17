package com.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String name;
    private String account;
    private String password;
    private String phone;
    private String checkCode;
    private Integer identity;
    private String founder;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", identity=" + identity +
                ", founder='" + founder + '\'' +
                ", modifier='" + modifier + '\'' +
                '}';
    }

    private String modifier;

    public User() {
    }

    public User(Integer id, String name, String account, String password, String phone, String checkCode, Integer identity, String founder, String modifier) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.checkCode = checkCode;
        this.identity = identity;
        this.founder = founder;
        this.modifier = modifier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}

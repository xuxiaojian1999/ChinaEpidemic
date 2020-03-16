package com.pojo;

import java.sql.Date;
//用于接收前端传来和数据库查询出来的数据,用于province中的成员
public class ProvinceItem {
    private Integer id;
    private String city;
    private String province;
    private Integer confirm;
    private Integer suspect;
    private Integer heal;
    private Integer dead;
    private String lastUpdateTime;
    private String modifier;
    private String day;

    public ProvinceItem() {
    }

    public ProvinceItem(Integer confirm, Integer suspect, Integer heal, Integer dead) {
        this.confirm = confirm;
        this.suspect = suspect;
        this.heal = heal;
        this.dead = dead;
    }

    public ProvinceItem(Integer id, String province, String city, Integer confirm, Integer suspect, Integer heal, Integer dead, String lastUpdateTime, String modifier, String day) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.confirm = confirm;
        this.suspect = suspect;
        this.heal = heal;
        this.dead = dead;
        this.lastUpdateTime = lastUpdateTime;
        this.modifier = modifier;
        this.day = day;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getSuspect() {
        return suspect;
    }

    public void setSuspect(Integer suspect) {
        this.suspect = suspect;
    }

    public Integer getHeal() {
        return heal;
    }

    public void setHeal(Integer heal) {
        this.heal = heal;
    }

    public Integer getDead() {
        return dead;
    }

    public void setDead(Integer dead) {
        this.dead = dead;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

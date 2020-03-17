package com.pojo;

import java.io.Serializable;

public class SimplProvince implements Serializable {
        private Integer id;
        private Integer confirm;
        private Integer suspect;
        private Integer heal;
        private Integer dead;
        private String lastUpdateTime;

    public SimplProvince() {
    }

    public SimplProvince(Integer confirm, Integer suspect, Integer heal, Integer dead) {
        this.confirm = confirm;
        this.suspect = suspect;
        this.heal = heal;
        this.dead = dead;
    }

    public SimplProvince(Integer confirm, Integer suspect, Integer heal, Integer dead, String lastUpdateTime) {
        this.confirm = confirm;
        this.suspect = suspect;
        this.heal = heal;
        this.dead = dead;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
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
}

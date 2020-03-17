package com.pojo;

import java.io.Serializable;
import java.util.List;

//用于传递给前端和存入redis
public class Province implements Serializable {
    private Local local;
    private SimplProvince todayData;
    private SimplProvince totalData;
    private String lastUpdateTime;
    private List<Province> city;

    public Province() {
    }

    public SimplProvince getTodayData() {
        return todayData;
    }

    public void setTodayData(SimplProvince todayData) {
        this.todayData = todayData;
    }

    public SimplProvince getTotalData() {
        return totalData;
    }

    public void setTotalData(SimplProvince totalData) {
        this.totalData = totalData;
    }

    public Province(Local local, SimplProvince todayData, SimplProvince totalData, String lastUpdateTime, List<Province> city) {
        this.local = local;
        this.todayData = todayData;
        this.totalData = totalData;
        this.lastUpdateTime = lastUpdateTime;
        this.city = city;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }



    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public List<Province> getCity() {
        return city;
    }

    public void setCity(List<Province> city) {
        this.city = city;
    }
}

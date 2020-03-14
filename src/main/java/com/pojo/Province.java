package com.pojo;

import java.util.List;

//用于传递给前端和存入redis
public class Province {
    private Local local;
    private ProvinceItem todayData;
    private ProvinceItem totalData;
    private String lastUpdateTime;
    private List<Province> city;

    public Province() {
    }

    public Province(Local local, ProvinceItem todayData, ProvinceItem totalData, String lastUpdateTime, List<Province> city) {
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

    public ProvinceItem getTodayData() {
        return todayData;
    }

    public void setTodayData(ProvinceItem todayData) {
        this.todayData = todayData;
    }

    public ProvinceItem getTotalData() {
        return totalData;
    }

    public void setTotalData(ProvinceItem totalData) {
        this.totalData = totalData;
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

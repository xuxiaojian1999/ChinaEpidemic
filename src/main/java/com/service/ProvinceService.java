package com.service;

import com.pojo.Province;

import java.util.List;

public interface ProvinceService {
    //用于获取前台展示的数据
    List<Province> getListFromRedis(String key);
    List<Province> getNewList();
    boolean modifyCity(Integer id,String city,Integer confirm,Integer suspect,Integer heal,Integer dead,
                       String modifier,String lastUpdateTime);
    boolean updateProvince();
}

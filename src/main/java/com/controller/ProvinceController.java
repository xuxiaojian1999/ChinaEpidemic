package com.controller;

import com.pojo.Province;
import com.pojo.ProvinceItem;
import com.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    ProvinceService provinceService;
    @RequestMapping("/getList")
    public List<Province> getList(String key){
        if (key!=null&key!=""){
            //key是需要在redis中查询的key
            return provinceService.getListFromRedis(key);
        }
        return null;
    }

    @RequestMapping("/getNewList")
    public List<Province> getNewList(){
        return provinceService.getNewList();
    }

    @RequestMapping("/modifyCity")
    public boolean modifyCity(@RequestBody ProvinceItem item){
        if (item!=null){
            Integer id =item.getId();
            String city=item.getCity();
            Integer confirm=item.getConfirm();
            Integer suspect=item.getSuspect();
            Integer heal=item.getHeal();
            Integer dead=item.getDead();
            String modifier=item.getModifier();
            String lastUpdateTime=item.getLastUpdateTime();
            return provinceService.modifyCity(id,city,confirm,suspect,heal,dead,modifier,lastUpdateTime);
        }
        return false;
    }
    @RequestMapping("/updateProvince")
    public boolean updateProvince(){
        return provinceService.updateProvince();
    }
}

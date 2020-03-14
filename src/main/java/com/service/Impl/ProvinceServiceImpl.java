package com.service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.dao.CityMapper;
import com.dao.ProvinceMapper;
import com.pojo.Local;
import com.pojo.Province;
import com.pojo.ProvinceItem;
import com.service.ProvinceService;
import com.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("ProvinceService")
public class ProvinceServiceImpl implements ProvinceService {
    //连接mysql
    @Resource
    private ProvinceMapper provinceMapper;
    @Resource
    private CityMapper cityMapper;
    //连接redis
    @Autowired
    private RedisUtils redisUtils;
    //用来装取从mysql中查询出来的数据
    //today
    private List<ProvinceItem> provinceTodayList=null;
    private List<ProvinceItem> cityTodayList=null;
    //total
    private List<ProvinceItem> provinceTotalList=null;
    private List<ProvinceItem> cityTotalList=null;
    //更新数据的天数
    private Integer updateIndex=-1;
    //查询存放在redis中昨日的today数据
    @Override
    public List<Province> getListFromRedis(String key) {
        List<Province> list=new ArrayList<>();
        //现在redis中查询是否存在key
        if (redisUtils.exists(key)){
            //存在,就从redis中取出
            String value= redisUtils.get(key);
            list = JSONArray.parseArray(value,Province.class);
        }
        //前台只读取redis中的数据
        //只从redis中取出，若redis中没有，前台就展示为空，就需要通知管理员进行更新
        return list;
    }
    //查询今日的today数据
    @Override
    public List<Province> getNewList() {
        //获取今天的时间
        String day=getDate(0);
        //查询今天的数据，查询结果存储在该类的变量中
        selectAllDate(day);
        //调用transProvinceList来获取数据库中的数据
        return transProvinceList();
    }

    @Override
    public boolean modifyCity(Integer id, String city, Integer confirm, Integer suspect, Integer heal, Integer dead, String modifier, String lastUpdateTime) {
        //获取今日的时间
        String day=getDate(0);
        if (cityMapper.updateCityByIdAndCityAndDay(id,city,confirm,
                suspect,heal,dead,modifier,lastUpdateTime,day)==1){
            //修改成功返回true
            return true;
        }
        return false;
    }
    //更新数据，更新时间为当天，更新到redis的数据为前一天的day，everyday_city_data中的day为当天
    @Override
    public boolean updateProvince() {
        boolean flag=false;
        //1.更新前一天的数据
        //a先查询前一天的每日的城市
        //查询前一天的数据
        Integer index=-1;
        //将cityTodayList置空
        cityTodayList=null;
        while (true&&index>-10){
            //一直循环直到查询数据,仅查询十次
            String time=getDate(index);
            cityTodayList=cityMapper.selectAllByDay(time);
            index--;
            //当查询出来的数据都不为空时，跳出循环
            if (cityTodayList!=null)break;
        }
        while (true){
            if (cityTodayList!=null){
                updateIndex=index;
                //当cityTodayList不为null，排除查询了前10天都没有数据
                //需要用到事务
                //b将每日城市数据更新到每日省份数据和城市数据中
                //c将每日省份数据更新到省份数据中
                //调用updateEPPCByEC对以上进行更新
                if (updateEPPCByEC())break;
                //更新成功退出循环
            }//当cityTodayList为null时，不进行处理
        }
        //2.更新redis中的数据
        //需要用到事务
        //a更新chinaData
        //b更新provincelist
        //c更新allprovincelist

        //3.更新每日城市数据（新建当天的数据，用于更新）
        //需要用到事务

        return flag;
    }


    //获取日期，
    // 参数 0为当日，-1为前一天
    //返回值字符串
    private String getDate(Integer index){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, index); //得到前一天
        Date date = calendar.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }
    //根据day查询province和city的全部数据
    //存放到该类上
    private void selectAllDate(String day){
        //用于装到today中的数据,查询everyday_province_data,everyday_city_data
            provinceTodayList=provinceMapper.selectAllByDay(day);
            cityTodayList=cityMapper.selectAllByDay(day);
        //用于装到total中的数据,查询province_data,city_data（这两个表中的数据行是固定的）
            provinceTotalList=provinceMapper.selectAll();
            cityTotalList=cityMapper.selectAll();
    }
    private List<Province> transProvinceList(){
        //用来装取全部的数据
        List<Province> list=new ArrayList<>();
        //进行格式转换
        for (ProvinceItem totalItem : provinceTotalList){
            //用来装取每个循环转换完成的数据
            //每个转换完成的数据，包含每个省份的total和today,包含城市list
            //城市list中包含每个城市的total和today
            Province province=new Province();
            //province的 local
            Local provinceLocal=new Local();
            provinceLocal.setProvince(totalItem.getProvince());
            province.setLocal(provinceLocal);
            //province的 lastupdatetime
            province.setLastUpdateTime(totalItem.getLastUpdateTime());
            //province的 totalData
            ProvinceItem totalData = transItemFromForeItem(totalItem);
            //将转换完的totaldata放入province
            province.setTotalData(totalData);
            //province的 todayData
            for (ProvinceItem todayItem: provinceTodayList) {
                //整个循环只有一次操作
                if (todayItem.getProvince().equals(totalItem.getProvince())){
                    //当todayItem属于totalItem中的省份
                    ProvinceItem todayData=transItemFromForeItem(totalItem);
                    //将转换完的todayData放入province
                    province.setTodayData(todayData);
                    //处理完后退出
                    break;
                }
            }
            //province的 city
            //用来装取city的数据
            List<Province> city=new ArrayList<>();
            for (ProvinceItem item : cityTotalList){
                if (item.getProvince().equals(totalItem.getProvince())){
                    //当该city属于该province，进行操作
                    Province cityItem=new Province();
                    //city的 local
                    Local cityLocal=new Local();
                    cityLocal.setCity(item.getCity());
                    cityLocal.setProvince(item.getProvince());
                    //city的 totalData
                    ProvinceItem cityTotalData=transItemFromForeItem(item);
                    //放入cityItem
                    cityItem.setTotalData(cityTotalData);
                    //city的 todayData
                    for(ProvinceItem todayItem : cityTodayList){
                        //整个循环只有一次操作
                        if (todayItem.getCity().equals(item.getCity())){
                            ProvinceItem cityTodayData=transItemFromForeItem(todayItem);
                            //放入cityItem
                            cityItem.setTodayData(cityTodayData);
                            break;
                        }
                    }

                }

            }
            list.add(province);
        }
        return list;
    }
    //用于将fore循环中的item转换为传递到前端的数据格式
    private ProvinceItem transItemFromForeItem(ProvinceItem Item) {
        ProvinceItem provinceItem=new ProvinceItem();
        provinceItem.setConfirm(Item.getConfirm());
        provinceItem.setDead(Item.getDead());
        provinceItem.setHeal(Item.getHeal());
        provinceItem.setSuspect(Item.getSuspect());
        provinceItem.setLastUpdateTime(Item.getLastUpdateTime());
        return provinceItem;
    }

    //启用事务对每日省份，省份，城市进行更新
    @Transactional
    protected boolean updateEPPCByEC(){
        boolean flag=false;
        //b将每日城市数据更新到城市数据中
        //查询全部城市数据
        cityTotalList=cityMapper.selectAll();
        for (ProvinceItem item : cityTotalList) {
            for (ProvinceItem i :cityTodayList){
                if (item.getCity().equals(i.getCity())){
                    //当城市相等，将todaylist中的数据加到totallist中
                    //设置为前一天的时间
                    //修改city_data数据库中的数据
                    if (cityMapper.updateCityByCityAndPro(item.getConfirm()+i.getConfirm(),
                            item.getSuspect()+i.getSuspect(),
                            item.getHeal()+i.getHeal(),
                            item.getDead()+i.getDead(),getDate(updateIndex),
                            item.getCity(),item.getProvince())==1)break;
                    break;
                }
            }
        }
        //c新增每日省份数据
        //重新从数据库查询各省份的数据
        //查询省份,用来循环省份
        provinceTotalList=provinceMapper.selectAll();
        for (ProvinceItem item:provinceTotalList) {
            //单个省份操作
            //查询该省份的城市
            cityTodayList=cityMapper.selectOneProvince(item.getProvince(),getDate(updateIndex));
            Integer confirm=0;
            Integer suspect=0;
            Integer heal=0;
            Integer dead=0;
            //循环将各城市的数据加到以上变量中
            for (ProvinceItem i: cityTodayList) {
                confirm=+i.getConfirm();
                suspect=+i.getSuspect();
                heal=+i.getHeal();
                dead=+i.getDead();
            }
                provinceMapper.insertProvinceByDayAndProvince(confirm,suspect,heal,dead,getDate(updateIndex),item.getProvince());
                //d将每日省份数据更新到省份数据中
                provinceMapper.updateProvinceByProvince(confirm+item.getConfirm(),suspect+item.getSuspect()
                        ,heal+item.getHeal(),dead+item.getDead(),getDate(updateIndex),item.getProvince());
            flag=true;
        }
        return flag;
    }
}

package com.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.CityMapper;
import com.dao.ProvinceMapper;
import com.pojo.Local;
import com.pojo.Province;
import com.pojo.ProvinceItem;
import com.pojo.SimplProvince;
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


    //查询今日的today数据,用于后台更新
    @Override
    public List<Province> getNewList() {
        //获取今天的时间
        String day=getDate(0);
        //查询今天的数据，查询结果存储在该类的变量中
        selectAllDate(day);
        //调用transProvinceList来获取数据库中的数据
        List<Province> list =transProvinceList(true);
        return list;
    }
    //更新everyday_city_data
    @Override
    public boolean modifyCity(Integer id, String city, Integer confirm, Integer suspect, Integer heal, Integer dead, String modifier, String lastUpdateTime) {
        //获取今日的时间
        String day=getDate(0);
        //在数据库中修改
        Integer flag=cityMapper.updateCityByIdAndCityAndDay(id,city,confirm,suspect,heal,dead,modifier,lastUpdateTime,day);
        if (flag==1){
            //修改成功返回true
            return true;
        }
        return false;
    }
    //更新数据，更新时间为当天，更新到redis的数据为前一天的day，
    //everyday_province_data,province_data,city_data,中的day为前一天
    // everyday_city_data中的day为当天
    //理论上每天只能更新一次
    @Override
    public boolean updateProvince() {
        boolean flag=false;
        //1.更新前一天的数据
        //a先查询前一天的每日的城市
        //查询前一天的数据
        Integer index=-1;
        //将cityTodayList置空
        cityTodayList=null;
        //更新没必要更新几天前的数据，只对前一天的数据进行更新
        String time=getDate(index);
        //获取前一天的everyday_province_data数据
        provinceTodayList=null;
        provinceTodayList=provinceMapper.selectAllByDay(time);
        boolean flag1=false;
        if (provinceTodayList.size()!=0){
            //provinceTodayList存在，就是已经更新过了
            flag1=true;
        }else {
            //查询everyday_city_data的数据，这个必须要有的
            cityTodayList=cityMapper.selectAllByDay(time);
            if (cityTodayList.size()!=0){
                updateIndex=index;
                //当cityTodayList不为null
                //需要用到事务
                //b将每日城市数据更新到每日省份数据和城市数据中
                //c将每日省份数据更新到省份数据中
                //调用updateEPPCByEC对以上进行更新
                flag1=updateEPPCByEC();
                //更新成功退出循环
            }
        }
        //2.更新redis中的数据
        //没有用到事务
        //a更新chinaData
        //b更新provincelist
        //c更新allprovincelist
        boolean flag2=updateRedis();
        //3.更新每日城市数据（新建当天的城市数据，用于更新）
        //需要用到事务
        boolean flag3=false;
        if (cityMapper.selectOneCity("湖北",getDate(0)).size()==0){
            flag3=updateEverydayCity();
        }else flag3=true;
        if (flag1&&flag2&&flag3)flag=true;
        return flag;
    }

    @Override
    public Province getChinaDataFromRedis(String key) {
        Province province=new Province();
        //现在redis中查询是否存在key
        if (redisUtils.exists(key)){
            //存在,就从redis中取出
            String value= redisUtils.get(key);
            province= JSONObject.parseObject(value,Province.class);
        }
        //前台只读取redis中的数据
        //只从redis中取出，若redis中没有，前台就展示为空，就需要通知管理员进行更新
        return province;
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
    private List<Province> transProvinceList(boolean cityIf){
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
            SimplProvince totalData = transItemFromForeItem(totalItem);
            //将转换完的totaldata放入province
            province.setTotalData(totalData);
            //province的 todayData
            for (ProvinceItem todayItem: provinceTodayList) {
                //整个循环只有一次操作
                if (todayItem.getProvince().equals(totalItem.getProvince())){
                    //当todayItem属于totalItem中的省份,将todayItem转化为todayData
                    SimplProvince todayData=transItemFromForeItem(todayItem);
                    //设置id
//                    todayData.setId(todayItem.getId());
                    //设置lastUpdateTime
                    todayData.setLastUpdateTime(todayItem.getLastUpdateTime());
                    //将转换完的todayData放入province
                    province.setTodayData(todayData);
                    //处理完后退出
                    break;
                }
            }
            //cityIf为true,则加上city
            if (cityIf){
                //province的 city
                //用来装取city的数据
                List<Province> city=new ArrayList<>();
                for (ProvinceItem item : cityTotalList){
                    if (item.getProvince().equals(totalItem.getProvince())){
                        //当该city属于该province，进行操作
                        //每个城市的数据
                        Province cityItem=new Province();
                        //city的 local
                        Local cityLocal=new Local();
                        cityLocal.setCity(item.getCity());
                        cityLocal.setProvince(item.getProvince());
                        cityItem.setLocal(cityLocal);
                        //city的 totalData
                        SimplProvince cityTotalData=transItemFromForeItem(item);
                        //放入cityItem
                        cityItem.setTotalData(cityTotalData);
                        //city的 todayData
                        for(ProvinceItem todayItem : cityTodayList){
                            //整个循环只有一次操作
                            if (todayItem.getCity().equals(item.getCity())){
                                SimplProvince cityTodayData=transItemFromForeItem(todayItem);
                                //设置id
                                cityTodayData.setId(todayItem.getId());
                                //设置lastUpdateTime
                                cityTodayData.setLastUpdateTime(todayItem.getLastUpdateTime());
                                //放入cityItem
                                cityItem.setTodayData(cityTodayData);
                                break;
                            }
                        }
                        //将转换好的每个城市的数据接入到城市list中
                        city.add(cityItem);
                    }
                }
                //给province设置city
                province.setCity(city);
            }
            list.add(province);
        }
        return list;
    }
    //用于将fore循环中的item转换为传递到前端的数据格式
    private SimplProvince transItemFromForeItem(ProvinceItem Item) {
        SimplProvince provinceItem=new SimplProvince(Item.getConfirm(),Item.getSuspect(),Item.getHeal(),Item.getDead(),Item.getLastUpdateTime());
        return provinceItem;
    }

    //启用事务对每日省份，省份，城市进行更新
    @Transactional
    protected boolean updateEPPCByEC(){
        boolean flag=false;
        //b将每日城市数据更新到城市数据中
        //查询city_data数据
        cityTotalList=cityMapper.selectAll();
        for (ProvinceItem item : cityTotalList) {
            for (ProvinceItem i :cityTodayList){
                if (item.getCity().equals(i.getCity())){
                    //当城市相等，将todaylist中的数据加到totallist中
                    //设置为前一天的时间
                    //修改city_data数据库中的数据
                    Integer suspect=item.getSuspect();
                    Integer suspect1=i.getSuspect();
                    if (suspect==null)suspect=0;
                    if (suspect1==null)suspect1=0;
                    cityMapper.updateCityByCityAndPro(item.getConfirm()+i.getConfirm(),
                            suspect+suspect1,
                            item.getHeal()+i.getHeal(),
                            item.getDead()+i.getDead(),getDate(updateIndex),
                            item.getCity(),item.getProvince());
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
            cityTodayList=cityMapper.selectOneCity(item.getProvince(),getDate(-1));
            Integer confirm=0;
            Integer suspect=0;
            Integer heal=0;
            Integer dead=0;
            //循环将各城市的数据加到以上变量中
            for (ProvinceItem i: cityTodayList) {
                Integer suspect1=i.getSuspect();
                if (suspect1==null)suspect1=0;
                confirm=+i.getConfirm();
                suspect=+suspect1;
                heal=+i.getHeal();
                dead=+i.getDead();
            }
                //更新每日省份数据
                provinceMapper.insertProvinceByDayAndProvince(confirm,suspect,heal,dead,getDate(updateIndex),item.getProvince());
                //d将每日省份数据更新到省份数据中
                //原来的省份数据加上新增的省份数据
                provinceMapper.updateProvinceByProvince(confirm+item.getConfirm(),suspect+item.getSuspect()
                        ,heal+item.getHeal(),dead+item.getDead(),getDate(updateIndex),item.getProvince());
            flag=true;
        }
        return flag;
    }
    //更新redis中的数据,用这个方法时，province和city的total和today数据必须更新完成
    private boolean updateRedis(){
        //没有用到事务
        Integer selectIndex=-1;
        //先查询前一天的数据
        selectAllDate(getDate(selectIndex));
        //a更新chinaData
        Local local=new Local();
        local.setCountry("中国");
        String lastUpdateTime=getDate(selectIndex);
        //使用省份的数据对chinaData进行更新
        SimplProvince totalData=countProvinceItem(0, 0, 0, 0, provinceTotalList);
        SimplProvince todayData = countProvinceItem(0, 0, 0, 0, provinceTodayList);
        //创建chinaData
        Province chinaData=new Province();
        chinaData.setLocal(local);
        chinaData.setLastUpdateTime(lastUpdateTime);
        chinaData.setTotalData(totalData);
        chinaData.setTodayData(todayData);
        String flag1=redisUtils.set("chinaData", JSONObject.toJSONString(chinaData));
        //b更新provincelist
        List<Province> list=transProvinceList(false);
        String flag2=redisUtils.set("provinceList",JSONArray.toJSONString(list));
        //c更新allprovincelist
        list=transProvinceList(true);
        String flag3=redisUtils.set("allProvinceList",JSONArray.toJSONString(list));
        //全部更新成功返回true
        if (flag1.equals("OK")&&flag2.equals("OK")&&flag3.equals("OK"))return true;
        else return false;

    }
    //将四个初始化的数据和要循环计算的list传入方法
    //返回一个计算完的对象
    private SimplProvince countProvinceItem(Integer confirm, Integer suspect, Integer heal, Integer dead, List<ProvinceItem> List) {
        for (ProvinceItem item: List) {
            confirm = confirm + item.getConfirm();
            suspect = suspect + item.getSuspect();
//                    ==null?0:item.getSuspect());
            heal = heal + item.getHeal();
            dead = dead + item.getDead();
        }
        SimplProvince todayData=new SimplProvince(confirm,suspect,heal,dead);
        return todayData;
    }
    //更新everyday_city_data
    @Transactional
    protected boolean updateEverydayCity(){
        String today=getDate(0);
        //查询数据库中的城市名称，在total中查询
        List<ProvinceItem> cityList=cityMapper.selectCity();
        //province
        List<Local> cpName=new ArrayList<>();
        for (ProvinceItem item : cityList) {
            //只是使用数据表中的城市名称
            cpName.add(new Local("",item.getProvince(),item.getCity()));
        }
        Integer flag= cityMapper.setNewCity(cpName,today);
            //新增成功返回true
            if(flag>=1)return true;
            else return false;
    }
}

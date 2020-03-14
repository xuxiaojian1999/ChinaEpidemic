package com.dao;

import com.pojo.ProvinceItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CityMapper {
    List<ProvinceItem> selectAllByDay(@Param("day") String day);
    List<ProvinceItem> selectAll();
    //更新的是everyday_city_data
    Integer updateCityByIdAndCityAndDay(@Param("id") Integer id,@Param("city") String city,@Param("confirm") Integer confirm,
                                        @Param("suspect") Integer suspect,@Param("heal") Integer heal,@Param("dead") Integer dead,
                                        @Param("modifier") String modifier,@Param("lastUpdateTime") String lastUpdateTime,
                                        @Param("day") String day);
    //更新的是city_data
    Integer updateCityByCityAndPro(@Param("confirm") Integer confirm,@Param("suspect") Integer suspect,
                                   @Param("heal") Integer heal, @Param("dead") Integer dead,
                                   @Param("lastUpdateTime") String lastUpdateTime,@Param("city") String city,@Param("province") String province);
    //查找everyday_city_data中的某个省份的数据的每日城市数据
    List<ProvinceItem> selectOneProvince(@Param("province") String province,@Param("day") String day);









}

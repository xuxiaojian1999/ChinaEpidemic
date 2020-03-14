package com.dao;

import com.pojo.ProvinceItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

@Mapper
public interface ProvinceMapper {
    List<ProvinceItem> selectAllByDay(@Param("day") String day);
    List<ProvinceItem> selectAll();
    List<ProvinceItem> selectProvince();
    //新增everyday_province_data
    Integer insertProvinceByDayAndProvince(@Param("confirm") Integer confirm,@Param("suspect") Integer suspect,
                                           @Param("heal") Integer heal,@Param("dead") Integer dead,
                                           @Param("day") String day,@Param("province") String province);
    //修改province_data
    Integer updateProvinceByProvince(@Param("confirm") Integer confirm,@Param("suspect") Integer suspect,
                                     @Param("heal") Integer heal,@Param("dead") Integer dead,
                                     @Param("lastUpdateTime") String  lastUpdateTime,@Param("province") String province);
















}

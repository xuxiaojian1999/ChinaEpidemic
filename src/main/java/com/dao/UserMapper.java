package com.dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    public User findUserByAP(@Param("account")String account, @Param("password")String password);
}

package com.dao;

import com.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectUserByAP(@Param("account")String account, @Param("password")String password);
    Integer updateCheckCode(@Param("checkCode")String checkCode);
    User selectCheckCodeAndNameByAI(@Param("account")String account,@Param("identity")Integer identity);
    List<User> selectAllUser();
    Integer updateIdentity(@Param("id") Integer id,@Param("modifier") String modifier);
    Integer updateUserById(@Param("id") Integer id,@Param("account") String account,@Param("password") String password,
                           @Param("name") String name,@Param("phone") String phone,@Param("identity") Integer identity,
                           @Param("modifier") String modifier);
    Integer insertUser(@Param("account") String account,@Param("password") String password,@Param("name") String name,
                       @Param("phone") String phone,@Param("identity") Integer identity,@Param("founder") String founder);
}

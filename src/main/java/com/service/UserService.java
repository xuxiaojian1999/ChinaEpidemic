package com.service;


import com.pojo.User;

import java.util.List;

public interface UserService {
//    实现登陆功能，登陆成功，返回user
    User login(String account,String password,String checkCode);
//    实现检验随机码功能，检验成功，返回user.name
    String checkCode(String account,Integer identity,String checkCode);
//    获取全部user列表
    List<User> getAllUserList();
//    删除user,删除成功返回true
    boolean deleteUserById(Integer id,String modifier);
//    修改User，修改成功返回true
    boolean modifyUser(Integer id,String account,String password,
                         String name,String phone,Integer identity,String modifier);
//      增加新的User
    boolean addUser(String account,String password,String name,
                    String phone,Integer identity,String founder);
}

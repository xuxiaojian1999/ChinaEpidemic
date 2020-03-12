package com.service.Impl;

import com.dao.UserMapper;
import com.pojo.User;
import com.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User login(String account, String password) {
        User user = userMapper.findUserByAP(account, password);
        if (user == null) {
            //查询结果为空
            //账号密码不正确，返回null
            return null;
        } else {
                //账号密码正确
                //将密码设置为空,返回其余属性
                user.setPassword("");
                return user;
        }
    }
}

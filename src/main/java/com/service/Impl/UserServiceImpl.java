package com.service.Impl;

import com.dao.UserMapper;
import com.pojo.User;
import com.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User login(String account, String password,String checkCode) {
        //在数据库中根据账号密码查询user(name,identity)
        User user = userMapper.selectUserByAP(account, password);
        if (user == null) {
            //查询结果为空
            //账号密码不正确，返回null
            return null;
        } else {
                //账号密码正确
                //user中只有identity,name
                //在数据库更新随机码
                //随机码在前端已经设置，无论数据库中是否设置成功，都要直接返回
               userMapper.updateCheckCode(checkCode,account);
               return user;
        }
    }

    @Override
    public String checkCode(String account, Integer identity, String checkCode) {
        User user= userMapper.selectCheckCodeAndNameByAI(account,identity);
        String dbCode=user.getCheckCode();
        if (dbCode==null){
            //只要查询出来的dbCode为空，就直接返回空
            return null;
        }else if (dbCode.equals(checkCode)){
            //当dbCode与前端传过来的checkCode一致时
            //将查询出的name返回
            String name=user.getName();
            return name;
        }
        return null;
    }

    @Override
    public List<User> getAllUserList() {
//        identity=0的user不展示
        return userMapper.selectAllUser();
    }

    @Override
    public boolean deleteUserById(Integer id, String modifier) {
//        默认值为false
        boolean flag=false;
        if (userMapper.updateIdentity(id,modifier)==1){
            //当返回值==1时，为数据库修改成功
            //将user的identity改为0，为不可见
            flag=true;//true删除成功
        }
        return flag;
    }

    @Override
    public boolean modifierUser(Integer id, String account, String password, String name, String phone, Integer identity, String modifier) {
//        默认值为false
        boolean flag=false;
        if (userMapper.updateUserById(id,account,password,name,phone,identity,modifier)==1){
            //当返回值==1时，为数据库修改成功
            flag=true;//true修改成功
        }
        return false;
    }

    @Override
    public boolean addUser(String account, String password, String name, String phone, Integer identity, String founder) {
        //默认值为false
        boolean flag=false;
        if (userMapper.insertUser(account,password,name,phone,identity,founder)==1){
            //当返回值==1时，为数据库增加成功
            //增加成功
            flag=true;
        }

        return flag;
    }
}

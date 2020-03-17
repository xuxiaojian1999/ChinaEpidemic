package com.controller;

import com.pojo.User;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/login")
    public User login(@RequestBody User user){
        //            判断user
        if (user!=null){
            //当user不为空
            String account =user.getAccount();
            String password =user.getPassword();
            String checkCode =user.getCheckCode();
            //直接返回返回值
            return userService.login(account,password,checkCode);
        }

        return null;
    }

    @RequestMapping("/check")
    public String checkCode(@RequestBody User user){
        //            判断user
        if (user!=null) {
            String account = user.getAccount();
            Integer identity = user.getIdentity();
            String checkCode = user.getCheckCode();
            return userService.checkCode(account, identity, checkCode);
        }
        return null;
    }

    @RequestMapping("/getList")
    public List<User> getAllUserList(){
        return userService.getAllUserList();
    }

    @RequestMapping("/deleteUser")
    public boolean deleteUser(@RequestBody User user){
        //            判断user
        if (user!=null) {
            Integer id = user.getId();
            String modifier = user.getModifier();
            return userService.deleteUserById(id, modifier);
        }
        return false;
    }

    @RequestMapping("/modifyUser")
    public boolean modifyUser(@RequestBody User user){
//        不修改checkCode
        //            判断user
        if (user!=null) {
            Integer id = user.getId();
            String account = user.getAccount();
            String password = user.getPassword();
            String name = user.getName();
            String phone = user.getPhone();
            Integer identity = user.getIdentity();
            String modifier = user.getModifier();
            return userService.modifyUser(id, account, password, name, phone, identity, modifier);
        }
        return false;
    }

    @RequestMapping("/addUser")
    public boolean addUser(@RequestBody User user){
        //不增加checkCode，id自动生成
        //            判断user
        if (user!=null) {
            String account = user.getAccount();
            String password = user.getPassword();
            String name = user.getName();
            String phone = user.getPhone();
            Integer identity = user.getIdentity();
            String founder = user.getFounder();
            return userService.addUser(account, password, name, phone, identity, founder);
        }
        return false;
    }

}

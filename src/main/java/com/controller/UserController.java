package com.controller;

import com.pojo.User;
import com.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/login")
    public User login(String account,String password){
        return userService.login(account,password);
    }
}

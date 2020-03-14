package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//页面跳转控制器
//不要用@RestController ,否则无法跳转到页面
@Controller
public class IndexController {
//    跳转到后台界面
    @RequestMapping("/backEnd")
    public String toBackEnd(){
        return "backstage.html";
    }
    @RequestMapping("/index")
    public String toFrontEnd(){
        return "front_desk.html";
    }
}

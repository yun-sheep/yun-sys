package com.yun.systest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description 测试各个功能
 * @auther j2-yizhiyang
 * @date 2023/3/19 16:28
 */
@RequestMapping("test")
@Controller
public class testcontroller {
    @RequestMapping("hello")
    String test(){
        System.out.println("hello,测试成功");
        return "hello,测试成功";
    }

}

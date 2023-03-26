package com.yun.login.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description  初始化自定义组件(要使用common中的user和jwt)
 * @auther j2-yizhiyang
 * @date 2023/3/25 19:04
 */
@Configuration
@ComponentScan({
        "com.yun.project.components.jwt",
        "com.yun.project.components.user"
})
public class Componentlnit {
}

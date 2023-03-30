package com.yun.login.config;

import com.yun.project.config.swagger.SwaggerCore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Description swagger配置类
 * @auther j2-yizhiyang
 * @date 2023/3/27 22:09
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean
    Docket loginApi() {
        return SwaggerCore.defaultDocketBuilder("登录模块", "com.yun.login.controller", "login");
    }
}

package com.yun.login.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Redis配置类
 * @auther j2-yizhiyang
 * @date 2023/3/25 19:07
 */
@Configuration
@ComponentScan({"com.yun.project.config.redis",
        "com.yun.project.utils.redis"
})
public class RedisInit {
}

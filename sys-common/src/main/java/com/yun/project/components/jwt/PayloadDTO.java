package com.yun.project.components.jwt;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Description 负载数据对象
 * @auther j2-yizhiyang
 * @date 2023/3/25 17:16
 */
@Data
@Builder
public class PayloadDTO {
    /**
     * 主体数据
     */
    private String sub;
    /**
     * 过期时间
     */
    private Long exp;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户拥有的权限
     */
    private List<String> authorities;
}

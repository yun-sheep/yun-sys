package com.yun.project.constants;

/**
 * @Description 授权常数
 * @auther j2-yizhiyang
 * @date 2023/3/23 20:24
 */
public interface AuthConstant {
    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";

    /**
     * JWT存储权限属性
     */
    String AUTHORITY_CLAIM_NAME = "authorities";

    /**
     * app端客户端类型定义
     */
    String CLIENT_APP = "sys-app";

    /**
     * 管理端客户端类型定义(这个是干什么的）
     */
    String CLIENT_MANAGER = "psi-manager";

    /**
     * 客户端密码
     */
    String CLIENT_PASSWORD = "123456";
}

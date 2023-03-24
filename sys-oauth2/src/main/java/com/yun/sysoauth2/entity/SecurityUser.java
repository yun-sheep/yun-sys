package com.yun.sysoauth2.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;

/**
 * @Description 权限认证用户实体类
 * @auther j2-yizhiyang
 * @date 2023/3/21 20:25
 */
@Getter
@Setter
@ToString
public class SecurityUser extends org.springframework.security.core.userdetails.User {
    /**
     * 关联一个用户对象
     */
    private SysUser user;

    /**
     * 关联一个机构编码
     */
    private String orgCode;

    /**
     * 构造初始化
     *
     * @param user        数据库的User对象
     * @param orgCode     数据库orgCode部门编码
     * @param authorities 权限列表
     */
    public SecurityUser(SysUser user, String orgCode, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
        this.orgCode  = orgCode;
    }
}

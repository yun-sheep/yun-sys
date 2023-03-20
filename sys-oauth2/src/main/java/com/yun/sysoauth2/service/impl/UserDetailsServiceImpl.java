package com.yun.sysoauth2.service.impl;

import com.yun.sysoauth2.service.ISysRoleService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 用户
 * @auther j2-yizhiyang
 * @date 2023/3/20 20:24
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    ISysRoleService roleService;
    @Resource
    HttpServletRequest request;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

package com.yun.sysoauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yun.project.constants.AuthConstant;
import com.yun.project.constants.TokenParams;
import com.yun.sysoauth2.entity.SecurityUser;
import com.yun.sysoauth2.entity.SysRole;
import com.yun.sysoauth2.entity.SysUser;
import com.yun.sysoauth2.service.ISysRoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 用户权限服务实现
 * @auther j2-yizhiyang
 * @date 2023/3/20 20:24
 */
//去追login->Oauth远程调用的模块
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    ISysRoleService roleService;
    @Resource
    HttpServletRequest request;
    @Resource
    SysUserServiceImpl userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //clientId = 登录客户端ID：project-app或project-manager
        String clientId = request.getParameter(TokenParams.CLIENT_ID);
        //为什么要通过客户端ID来
        if (AuthConstant.CLIENT_MANAGER.equals(clientId)) {
            //1 通过用户名查找用户对象
            SysUser user = new SysUser();
            user.setUsername(username);

            user = userService.getOne(new QueryWrapper<>(user));
            if (user == null) {
                throw new UsernameNotFoundException("用户名或密码错误");
            }

            //2 通过用户ID获取角色列表
            List<SysRole> roles = roleService.listRoleByUserId(user.getId());
             /*
            通过username查询orgCode
             */
            String orgCode = roleService.getOrgCodeByUsername(user.getUsername());

            //3 将数据库角色转换成Security权限对象
            List<GrantedAuthority> authorities = new ArrayList<>();
            //这个是直接写死的吗这个权限？
            roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleCode())));
            //4 构建权限角色对象（这个应该直接存储了啊，不然在哪里进行存储啊，妈的，无语） TODO: 扩展存储对象在这里通过数据库查询获取并注入到SecurityUser对象中
            return new SecurityUser(user, orgCode, authorities);

        } else if (AuthConstant.CLIENT_APP.equals(clientId)) {
            throw new UsernameNotFoundException("用户端查找用户尚未实现");
        }
        throw new UsernameNotFoundException("登录客户端ID错误");

    }
}

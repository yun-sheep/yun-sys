package com.yun.testoauthserver.service.Impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yun.project.components.user.UserDTO;
import com.yun.testoauthserver.entity.SecurityUser;
import com.yun.testoauthserver.entity.SysRole;
import com.yun.testoauthserver.entity.SysUser;
import com.yun.testoauthserver.entity.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @Description  用户权限权限服务实现
 * @auther j2-yizhiyang
 * @date 2023/3/29 18:50
 */
@Service
public class UserServiceImpl implements UserDetailsService {
    private List<UserDto> userList;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysUserServiceImpl userService;

    @Resource
    private SysRoleServiceImpl roleService;

    @PostConstruct
    public void initData() {
        //
        String password = passwordEncoder.encode("123456");
        System.out.println(password);
        userList = new ArrayList<>();
        userList.add(new UserDto(1L,"macro", password,1, CollUtil.toList("ADMIN")));
        userList.add(new UserDto(2L,"andy", password,1, CollUtil.toList("TEST")));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*List<UserDto> findUserList = userList.stream().filter(item -> item.getUsername().equals(username)).collect(Collectors.toList());
        if (CollUtil.isEmpty(findUserList)) {
            throw new UsernameNotFoundException("用户密码错误");
        }
        SecurityUser securityUser = new SecurityUser(findUserList.get(0));
        QueryWrapper<SysUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        //从mysql中获取用户和密码
        SysUser sysUser = userService.getOne(userQueryWrapper);
        if(sysUser == null){
            throw new UsernameNotFoundException("用户密码错误");
        }
        else {

            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        }*/
        SysUser user = new SysUser();
        user.setUsername(username);

        user = userService.getOne(new QueryWrapper<>(user));
        if (user == null) {
            throw new UsernameNotFoundException("用户名,密码错误");
        }

        //2 通过用户ID获取角色列表
        /*List<SysRole> roles = roleService.listRoleByUserId(user.getId());

            /*
            通过username查询orgCode

        String orgCode = roleService.getOrgCodeByUsername(user.getUsername());

        //3 将数据库角色转换成Security权限对象
        List<GrantedAuthority> authorities = new ArrayList<>();
        //这个是直接写死的吗这个权限？
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleCode())));
        //4 构建权限角色对象(这个是权限对象是在哪里用的，怎么传的) TODO: 扩展存储对象在这里通过数据库查询获取并注入到SecurityUser对象中

         */
       SecurityUser securityUser =  new SecurityUser(user);
       //TODO 设置权限
       //securityUser.setAuthorities(authorities);
        if (!securityUser.isEnabled()) {
            throw new DisabledException("无权限1");
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException("账号封锁");
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException("账号过期");
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("验证过期");
        }
        return securityUser;

    }

}

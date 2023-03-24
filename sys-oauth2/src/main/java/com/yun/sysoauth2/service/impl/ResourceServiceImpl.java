package com.yun.sysoauth2.service.impl;

import com.yun.project.constants.RedisConstant;
import com.yun.sysoauth2.entity.SysMenu;
import com.yun.sysoauth2.entity.SysRole;
import com.yun.sysoauth2.service.ISysMenuService;
import com.yun.sysoauth2.service.ISysRoleService;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/3/20 21:11
 */
public class ResourceServiceImpl {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private ISysMenuService menuService;
    @Resource
    private ISysRoleService roleService;
    //使用PostConstruct注解，程序在启动的时候就加载某些数据
    @PostConstruct
    public void init() {
        // 定义缓存map
        Map<String, List<String>> resourceRolesMap = new TreeMap<>();
        // 1 获取所有菜单
        List<SysMenu> tMenus = menuService.listAllLinkUrl();
        tMenus.forEach(menu -> {
            // 2 获取菜单对应的角色
            List<SysRole> rolesMenu = roleService.listRoleByMenuPath(menu.getUrl());
            List<String> roles = new ArrayList<>();
            rolesMenu.forEach(role -> roles.add(role.getRoleCode()));
            resourceRolesMap.put(menu.getUrl(), roles);
        });

        //将资源缓存到redis
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }

}

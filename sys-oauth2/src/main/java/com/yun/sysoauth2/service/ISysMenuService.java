package com.yun.sysoauth2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yun.sysoauth2.entity.SysMenu;

import java.util.List;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/3/20 21:15
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 获取菜单中的链接地址
     * @return 查询结果
     */
    List<SysMenu> listAllLinkUrl();
}

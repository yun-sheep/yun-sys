package com.yun.sysoauth2.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.sysoauth2.entity.SysRole;
import com.yun.sysoauth2.mapper.SysRoleMapper;
import com.yun.sysoauth2.service.ISysRoleService;

import java.util.List;

/**
 * @Description 角色表的服务类
 * @auther j2-yizhiyang
 * @date 2023/3/20 20:31
 */
public class ISysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements ISysRoleService {
    @Override
    public List<SysRole> listRoleByUserId(String userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<SysRole> listRoleByMenuPath(String path) {
        return baseMapper.selectByMenuPath(path);
    }

    @Override
    public String getOrgCodeByUsername(String username) {
        return baseMapper.selectOrgCodeByUsername(username);
    }
}

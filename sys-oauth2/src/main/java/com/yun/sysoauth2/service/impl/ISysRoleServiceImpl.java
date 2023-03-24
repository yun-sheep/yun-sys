package com.yun.sysoauth2.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.sysoauth2.entity.SysRole;
import com.yun.sysoauth2.mapper.SysRoleMapper;
import com.yun.sysoauth2.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 角色表的服务类
 * @auther j2-yizhiyang
 * @date 2023/3/20 20:31
 */
@Service
public class ISysRoleServiceImpl extends ServiceImpl<SysRoleMapper,SysRole> implements ISysRoleService {
    /**
     * 获取所有角色通过userID
     * */
    @Override
    public List<SysRole> listRoleByUserId(String userId) {
        return baseMapper.selectByUserId(userId);
    }
    /**
     * 通过菜单路径获取对应的角色
     * @param path 菜单路径
     * @return 角色列表
     */
    @Override
    public List<SysRole> listRoleByMenuPath(String path) {
        //怎么能这样写呢
        return baseMapper.selectByMenuPath(path);
    }

    @Override
    public String getOrgCodeByUsername(String username) {
        return baseMapper.selectOrgCodeByUsername(username);
    }
}

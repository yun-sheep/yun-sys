package com.yun.testoauthserver.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.testoauthserver.Mapper.SysRoleMapper;
import com.yun.testoauthserver.entity.SysRole;
import com.yun.testoauthserver.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Gerins
 * @since 2023-02-22
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
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

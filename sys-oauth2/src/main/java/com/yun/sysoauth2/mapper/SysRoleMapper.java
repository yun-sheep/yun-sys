package com.yun.sysoauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yun.sysoauth2.entity.SysRole;

import java.util.List;

/**
 * @Description role的mapper接口
 * @auther j2-yizhiyang
 * @date 2023/3/20 20:35
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 通过用户编号查询角色
     * @param userId 用户编号
     * @return 角色列表
     */
    List<SysRole> selectByUserId(String userId);

    /**
     * 通过菜单路径获取对应的角色
     * @param path 菜单路径
     * @return 角色列表
     */
    List<SysRole> selectByMenuPath(String path);

    /**
     * 通过用户名查询机构编码
     * @param username 用户名
     * @return 机构编码
     */
    String selectOrgCodeByUsername(String username);
}

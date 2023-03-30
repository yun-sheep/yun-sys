package com.yun.testoauthserver.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.yun.testoauthserver.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Gerins
 * @since 2023-02-22
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}

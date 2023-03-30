package com.yun.testoauthserver.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.testoauthserver.Mapper.SysUserMapper;
import com.yun.testoauthserver.entity.SysUser;
import com.yun.testoauthserver.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Gerins
 * @since 2023-02-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}

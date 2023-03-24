package com.yun.sysoauth2.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.sysoauth2.entity.SysUser;
import com.yun.sysoauth2.mapper.SysUserMapper;
import com.yun.sysoauth2.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/3/23 20:20
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
}

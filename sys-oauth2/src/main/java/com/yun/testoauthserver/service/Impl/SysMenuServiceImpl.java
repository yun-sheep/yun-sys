package com.yun.testoauthserver.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.testoauthserver.Mapper.SysMenuMapper;
import com.yun.testoauthserver.entity.SysMenu;
import com.yun.testoauthserver.service.ISysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gerins
 * @since 2023-02-22
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<SysMenu> listAllLinkUrl() {
        LambdaQueryWrapper<SysMenu> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(SysMenu::getUrl);
        lambdaQueryWrapper.isNotNull(SysMenu::getUrl);
        return baseMapper.selectList(lambdaQueryWrapper);
    }

}

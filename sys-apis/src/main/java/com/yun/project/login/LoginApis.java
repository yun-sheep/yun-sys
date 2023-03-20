package com.yun.project.login;


import com.yun.project.dto.login.LoginDTO;
import com.yun.project.dto.login.Oauth2TokenDTO;
import com.yun.project.vo.JsonVO;
import com.yun.project.vo.LoginVO;

/**
 * @Description 用户登录api
 * @auther j2-yizhiyang
 * @date 2023/3/19 20:13
 */
public interface LoginApis {
    /**
     * 授权登录接口
     * @param loginDTO 登录数据
     * @return 授权登录结果
     */
    JsonVO<Oauth2TokenDTO> authLogin(LoginDTO loginDTO);

    /**
     * 刷新Token认证
     * @param oauth2TokenDTO Token数据对象
     * @return 刷新Token结果
     */
    JsonVO<Oauth2TokenDTO> refreshToken(Oauth2TokenDTO oauth2TokenDTO);

    /**
     * 获取当前用户信息
     * @return 返回当前用户信息
     */
    JsonVO<LoginVO> getCurrUser();

    /**
     * 退出登录
     * @return 退出结果
     */
    JsonVO<String> logout();
}

package com.yun.sysoauth2.controller;

import com.yun.project.dto.login.Oauth2TokenDTO;
import com.yun.project.oauth.AuthApis;
import com.yun.project.vo.JsonVO;

import java.security.Principal;
import java.util.Map;

/**
 * @Description 认证服务相关接口
 * @auther j2-yizhiyang
 * @date 2023/3/20 20:05
 */
public class AuthController implements AuthApis {
    /**
     * 查询
     *
     * */
    @Override
    public JsonVO<Oauth2TokenDTO> postAccessToken(Principal principal, Map<String, String> parameters) {
        return null;
    }
}

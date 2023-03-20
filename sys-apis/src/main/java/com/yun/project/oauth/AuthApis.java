package com.yun.project.oauth;

import com.yun.project.dto.login.Oauth2TokenDTO;
import com.yun.project.vo.JsonVO;

import java.security.Principal;
import java.util.Map;

/**
 * @Description 认证授权接口
 * @auther j2-yizhiyang
 * @date 2023/3/20 20:06
 */
public interface AuthApis {
    /**
     * 授权认证接口
     * @param principal  安全主体对象
     * @param parameters 传递参数
     * @return 响应结果
     */
    JsonVO<Oauth2TokenDTO> postAccessToken(Principal principal, Map<String, String> parameters);
}

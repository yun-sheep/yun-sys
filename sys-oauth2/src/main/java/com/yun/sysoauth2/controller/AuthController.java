package com.yun.sysoauth2.controller;

import com.yun.project.constants.TokenParams;
import com.yun.project.dto.login.Oauth2TokenDTO;
import com.yun.project.oauth.AuthApis;
import com.yun.project.vo.JsonVO;
import com.yun.project.vo.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Map;

/**
 * @Description 认证服务相关接口
 * @auther j2-yizhiyang
 * @date 2023/3/20 20:05
 */
@RequestMapping("oauth")
public class AuthController implements AuthApis {
    private final TokenEndpoint tokenEndpoint;

    @Autowired
    public AuthController(TokenEndpoint tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }
    /**
     * 查询
     *
     * */
    @Override
    @PostMapping("token")
    public JsonVO<Oauth2TokenDTO> postAccessToken(Principal principal, Map<String, String> parameters) {
        //调用oAuth2AccessToken的postAccessToken接口来刷新或颁发token
        OAuth2AccessToken oAuth2AccessToken;
        try {
            oAuth2AccessToken = tokenEndpoint.postAccessToken(principal,parameters).getBody();
        } catch (Exception e) {
            return JsonVO.create(null, ResultStatus.FAIL.getCode(), "postAccessToken:" + e.getClass().getSimpleName() + ":" + e.getMessage());
        }
        if(oAuth2AccessToken!=null){
            //构建传送给前端的token
            Oauth2TokenDTO oauth2TokenDTO = new Oauth2TokenDTO(
                    oAuth2AccessToken.getValue(),
                    oAuth2AccessToken.getRefreshToken().getValue(),
                    "Bearer ",
                    oAuth2AccessToken.getExpiresIn(),
                    parameters.get(TokenParams.CLIENT_ID));
            return JsonVO.create(oauth2TokenDTO,ResultStatus.SUCCESS);
        }
        return JsonVO.create(null, ResultStatus.FAIL.getCode(), "oAuth2AccessToken为null");
    }
}

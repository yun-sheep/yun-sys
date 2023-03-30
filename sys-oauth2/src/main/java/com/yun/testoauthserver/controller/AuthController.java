package com.yun.testoauthserver.controller;

import com.yun.project.dto.login.Oauth2TokenDTO;
import com.yun.project.vo.JsonVO;
import com.yun.project.vo.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

/**
 * @Description 自定义Oauth2获取令牌
 * @auther j2-yizhiyang
 * @date 2023/3/30 16:33
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Resource
    private TokenEndpoint tokenEndpoint;

    @PostMapping("/token")
    public JsonVO<Oauth2TokenDTO>  postAccessToken(Principal principal, @RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException{
        //调用oAuth2AccessToken的postAccessToken接口来刷新或颁发token
        OAuth2AccessToken oAuth2AccessToken;
        try {
            oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        } catch (Exception e) {
            return JsonVO.create(null, ResultStatus.FAIL.getCode(), "postAccessToken:" + e.getClass().getSimpleName() + ":" + e.getMessage());
        }
        if (oAuth2AccessToken != null) {
            Oauth2TokenDTO oauth2TokenDto = new Oauth2TokenDTO(
                    oAuth2AccessToken.getValue(),
                    oAuth2AccessToken.getRefreshToken().getValue(),
                    "Bearer ",
                    oAuth2AccessToken.getExpiresIn(),
                    parameters.get("client_id"));
            return JsonVO.create(oauth2TokenDto, ResultStatus.SUCCESS);
        }
        return JsonVO.create(null, ResultStatus.FAIL.getCode(), "oAuth2AccessToken为null");
    }


}

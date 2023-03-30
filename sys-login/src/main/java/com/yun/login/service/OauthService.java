package com.yun.login.service;

import com.yun.login.fallback.OauthServiceFallbackFactory;
import com.yun.project.dto.login.Oauth2TokenDTO;
import com.yun.project.vo.JsonVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/3/30 17:07
 */
@FeignClient(value = "micro-oauth2-auth", fallbackFactory = OauthServiceFallbackFactory.class)
public interface OauthService {
    @PostMapping("/oauth/token")
    JsonVO<Oauth2TokenDTO> postAccessToken(@RequestParam Map<String, String> parameters);
}

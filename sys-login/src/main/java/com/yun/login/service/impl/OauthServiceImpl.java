package com.yun.login.service.impl;

import com.yun.login.service.OauthService;
import com.yun.project.dto.login.Oauth2TokenDTO;
import com.yun.project.vo.JsonVO;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @Description 授权服务类
 * @auther j2-yizhiyang
 * @date 2023/3/30 17:07
 */
@AllArgsConstructor
public class OauthServiceImpl implements OauthService {
    private Throwable throwable;

    private void setMessage(JsonVO<?> vo) {
        if (throwable.getMessage() != null) {
            vo.setMessage(throwable.getMessage());
        } else {
            vo.setMessage(throwable.getClass().toGenericString());
        }
    }
    //这个涉及到了什么（为什么要把login和oauth2模块分开啊）：
    @Override
    public JsonVO<Oauth2TokenDTO> postAccessToken(Map<String, String> parameters) {
        JsonVO<Oauth2TokenDTO> vo = JsonVO.fail(null);
        setMessage(vo);
        return vo;
    }
}

package com.yun.login.fallback;

import com.yun.login.service.impl.OauthServiceImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/3/30 17:06
 */
@Component
public class OauthServiceFallbackFactory implements FallbackFactory<OauthServiceImpl> {
    @Override
    public OauthServiceImpl create(Throwable throwable) {
        return new OauthServiceImpl(throwable);
    }
}

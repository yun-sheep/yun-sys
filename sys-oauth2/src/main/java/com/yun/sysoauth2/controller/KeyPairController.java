package com.yun.sysoauth2.controller;

import com.yun.project.oauth.KeyPairApis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;


import java.util.Map;

/**
 * @Description 获取RSA密钥接口
 * @auther j2-yizhiyang
 * @date 2023/3/24 16:11
 */
@RestController
@RequestMapping("rsa")
public class KeyPairController implements KeyPairApis {
    private final KeyPair keyPair;

    @Autowired
    public KeyPairController(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Override
    @GetMapping("public-key")
    public Map<String, Object> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

    @Override
    @GetMapping("private-key")
    public Map<String, Object> getPrivateKey() {
        return null;
    }
}

package com.yun.project.oauth;

import java.util.Map;

/**
 * @Description 密钥接口
 * @auther j2-yizhiyang
 * @date 2023/3/24 16:18
 */
public interface KeyPairApis {
    /**
     * 获取公钥
     * @return 返回公钥数据
     */
    Map<String, Object> getPublicKey();

    /**
     * 获取私钥
     * @return 返回私钥数据
     */
    Map<String, Object> getPrivateKey();
}

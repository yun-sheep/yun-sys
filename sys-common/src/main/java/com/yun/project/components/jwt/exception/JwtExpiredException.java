package com.yun.project.components.jwt.exception;

/**
 * @Description Token过期异常定义
 * @auther j2-yizhiyang
 * @date 2023/3/25 17:21
 */
public class JwtExpiredException extends RuntimeException{
    public JwtExpiredException(String message) {
        super(message);
    }
}

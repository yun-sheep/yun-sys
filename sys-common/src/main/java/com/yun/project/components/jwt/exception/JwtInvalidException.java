package com.yun.project.components.jwt.exception;

/**
 * @Description token无效异常
 * @auther j2-yizhiyang
 * @date 2023/3/25 17:22
 */
public class JwtInvalidException extends RuntimeException{
    public JwtInvalidException(String message) {
        super(message);
    }
}

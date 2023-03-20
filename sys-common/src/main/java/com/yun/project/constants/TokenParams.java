package com.yun.project.constants;

/**
 * @Description login传递给oauth2生成Token的参数
 * @auther j2-yizhiyang
 * @date 2023/3/20 16:22
 */
public class TokenParams {
    //参数个数
    public static Integer PARAMS_COUNT = 5;
    //验证方式
    public static String GRANT_TYPE = "grant_type";
    //用户id,client_id;
    public static String CLIENT_ID = "client_id";
    //client_secret
    public static String CLIENT_SECRET = "client_secret";
    //用户名username
    public static String USERNAME = "username";
    //用户密码password
    public static String PASSWORD = "password";

}

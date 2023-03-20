package com.yun.project.components.user;

import com.yun.project.components.jwt.JwtComponent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 获取登用户信息
 * @auther j2-yizhiyang
 * @date 2023/3/20 15:52
 */
public class UserHolder {
    @Resource
    JwtComponent jwtComponent;

    /**
     * 从请求头中获取用户信息
     * @return 用户信息
     * @throws Exception 解析失败抛出异常
     */
    public UserDTO getCurrenUser() throws Exception{
        //从header中获取信息
        ServletRequestAttributes servletRequestAttributes
                 = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes == null){
            return null;
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //获取request头中的用户信息
        String userStr = request.getHeader("user");
        /*if(userStr == null){

        }*/
        return null;
    }


}

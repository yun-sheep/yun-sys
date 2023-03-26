package com.yun.project.components.user;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.yun.project.components.jwt.JwtComponent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description 获取登用户信息(获取当前登录信息，在对用户密码丢该，退出登录的时候要用）
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
        //从header中获取信息（为什么要这样设计啊，直接传UserID不行吗）
        ServletRequestAttributes servletRequestAttributes
                 = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes == null){
            return null;
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //获取request头中的用户信息
        String userStr = request.getHeader("user");
        if(userStr == null){
            //从token中解析用户信息并设置到Header中去
            String realToken = request.getHeader("Authorization").replace("Bearer ", "");
            userStr = jwtComponent.defaultRsaVerify(realToken);
        }
        JSONObject userJsonObject = new JSONObject(userStr);
        return UserDTO.builder()
                .id(Convert.toStr(userJsonObject.get("id")))
                .username(userJsonObject.getStr("user_name"))
                .isEnabled(Convert.toBool(1))
                .roles(Convert.toList(String.class, userJsonObject.get("authorities")))
                .orgCode(userJsonObject.getStr("org_code"))
                .build();
    }
    public String getCurrentUserToken() throws Exception {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return null;
        }

        // 获取请求Request中的token
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request.getHeader("Authorization").replace("Bearer ", "");
    }


}

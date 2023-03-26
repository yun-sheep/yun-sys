package com.yun.project.components.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/3/20 15:56
 */
@Data
@Builder
public class UserDTO {
    /**
     * 用户编号
     */
    private String id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 是否启用
     */
    private Boolean isEnabled;
    /**
     * 用户拥有角色列表
     */
    private List<String> roles;
    /**
     * 机构编码
     */
    private String orgCode;
}

package com.yun.project.dto.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 用户登录传输数据
 * @auther j2-yizhiyang
 * @date 2023/3/19 20:15
 */
@ApiModel("登录上传对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", example = "admin", required = true)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", example = "123456", required = true)
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", example = "999818")
    private String code;

    /**
     * 登录客户端ID
     */
    @ApiModelProperty(value = "登录客户端ID：project-app或project-manager", example = "project-manager", required = true)
    private String clientId;
}

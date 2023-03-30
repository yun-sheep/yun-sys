package com.yun.project.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 描述：登录显示数据对象
 * </p>

 */
@ApiModel("登录显示对象")
@Data
public class LoginVO {
    @ApiModelProperty(value = "用户唯一编号", example = "1")
    private String id;

    @ApiModelProperty(value = "用户名", example = "admin")
    private String username;

    @ApiModelProperty(value = "是否启用 1 启用 0 禁用 ", example = "1")
    private Boolean isEnabled;

    @ApiModelProperty(value = "用户角色列表", example = "['ADMIN','MANAGER']")
    private List<String> roles;

    @ApiModelProperty(value = "机构编码", example = "A01A103")
    private String orgCode;
}

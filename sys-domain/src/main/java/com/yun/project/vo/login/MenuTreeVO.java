package com.yun.project.vo.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 描述：树状菜单显示数据
 * </p>
 */
@Data
public class MenuTreeVO {
    @ApiModelProperty(value = "序号", example = "1")
    private String id;
    @ApiModelProperty(value = "菜单名称", example = "获取菜单列表")
    private String name;
    @ApiModelProperty(value = "路由地址", example = "")
    private String path;
    @ApiModelProperty(value = "图标", example = "fa-stethoscope")
    private String icon;
    @ApiModelProperty(value = "父级菜单编号", example = "")
    private String parentMenuId;
}

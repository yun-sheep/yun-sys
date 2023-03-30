package com.yun.testoauthserver.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/3/30 15:10
 */
@Getter
@Setter
@TableName("sys_menu")
public class SysMenu {
    private static final long serialVersionUID = 1L;

    private String id;

    private String parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Double sortNo;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
}

package com.yun.testoauthserver.entity;

import lombok.*;

import java.util.List;

/**
 * @Description
 * @auther j2-yizhiyang
 * @date 2023/3/29 18:58
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private List<String> roles;
}

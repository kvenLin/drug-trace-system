package com.uchain.drugtracesystem.model.view;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: clf
 * @Date: 19-2-10
 * @Description:
 */
@Data
public class JwtAuthenticationRequest {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
}

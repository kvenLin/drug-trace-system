package com.uchain.drugtracesystem.model.view;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Author: clf
 * @Date: 19-2-11
 * @Description:
 */
@Data
public class RegisterRequest {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能少于6位字符")
    private String password;
    @NotNull(message = "用户角色不能为空")
    private Long roleId;
}

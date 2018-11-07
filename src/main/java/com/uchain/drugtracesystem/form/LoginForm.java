package com.uchain.drugtracesystem.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginForm {
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    private String userId;
    @NotNull(message = "密码不能为空")
    @ApiModelProperty("密码")
    private String password;
}

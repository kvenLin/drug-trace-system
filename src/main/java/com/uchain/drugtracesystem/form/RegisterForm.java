package com.uchain.drugtracesystem.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterForm {
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("确认密码")
    private String checkPassword;
}

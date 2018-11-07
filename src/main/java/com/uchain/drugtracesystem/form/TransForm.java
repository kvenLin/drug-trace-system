package com.uchain.drugtracesystem.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TransForm {
    @ApiModelProperty("药品id")
    private String drugId;
    @ApiModelProperty("中转代理用户id")
    private String agencyId;
    @ApiModelProperty("中转地址")
    private String place;
}

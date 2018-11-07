package com.uchain.drugtracesystem.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BuyForm {
    @ApiModelProperty("药品id")
    private String drugId;
    @ApiModelProperty("购买者id")
    private String buyerId;
    @ApiModelProperty("交易地址")
    private String endPlace;
}

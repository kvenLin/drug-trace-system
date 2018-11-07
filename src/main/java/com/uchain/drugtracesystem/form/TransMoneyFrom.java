package com.uchain.drugtracesystem.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransMoneyFrom {
    @ApiModelProperty("购买者Id")
    private String buyerId;
    @ApiModelProperty("出售者Id")
    private String sellerId;
    @ApiModelProperty("价格")
    private BigDecimal price;
}

package com.uchain.drugtracesystem.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DrugInitForm {
    @ApiModelProperty("药品id")
    private String drugId;
    @ApiModelProperty("拥有者id")
    private String ownerId;
    @ApiModelProperty("药品名称")
    private String name;
    @ApiModelProperty("价格")
    private BigDecimal price;
    @ApiModelProperty("文件的hash")
    private String fileHash;
    @ApiModelProperty("描述信息")
    private String description;
}

package com.uchain.drugtracesystem.form.drug;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: clf
 * @Date: 19-2-20
 * @Description:
 */
@Data
public class DrugForm {
    @NotNull(message = "生产地址不能为空")
    @ApiModelProperty("生产地址")
    private String address;
    @NotNull(message = "起始规定价格不能为空")
    @ApiModelProperty("起始规定价格")
    private Long price;
    @NotNull(message = "药品名称不能为空")
    @ApiModelProperty("药品名称")
    private String name;
    @NotNull(message = "药品信息描述不能为空")
    @ApiModelProperty("药品信息描述")
    private String description;
}

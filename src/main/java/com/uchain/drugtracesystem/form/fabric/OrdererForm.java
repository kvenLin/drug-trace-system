package com.uchain.drugtracesystem.form.fabric;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: clf
 * @Date: 19-2-17
 * @Description:
 */
@Data
public class OrdererForm {
    @NotNull(message = "排序节点名不能为空")
    private String ordererName;
    @NotNull(message = "排序节点ip地址不能为空")
    private String ordererLocation;
}

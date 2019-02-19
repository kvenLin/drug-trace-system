package com.uchain.drugtracesystem.form.fabric;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: clf
 * @Date: 19-2-19
 * @Description:
 */
@Data
public class ChaincodeUpdateForm extends ChaincodeForm{
    @NotNull(message = "链码id不能为空")
    private Long id;
}

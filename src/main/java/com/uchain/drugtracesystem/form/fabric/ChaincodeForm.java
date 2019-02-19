package com.uchain.drugtracesystem.form.fabric;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: clf
 * @Date: 19-2-19
 * @Description:
 */
@Data
public class ChaincodeForm {
    @NotNull(message = "通道名称不能为空")
    private String channelName;
    @NotNull(message = "链码名称不能为空")
    private String chaincodeName;
    @NotNull(message = "链码路径不能为空")
    private String chaincodePath;
    @NotNull(message = "链码版本不能为空")
    private String chaincodeVersion;
    @ApiModelProperty("请求响应等待最长时间,可以为空")
    private Integer invokeWaitTime;
    @ApiModelProperty("交易完成等待时间")
    private Integer deployWaitTime;
}

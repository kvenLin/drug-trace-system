package com.uchain.drugtracesystem.model.VO;

import lombok.Data;

/**
 * @Author: clf
 * @Date: 19-2-19
 * @Description:
 */
@Data
public class ChaincodeVO {
    private Long id;
    private String channelName;
    private String chaincodeName;
    private String chaincodePath;
    private String chaincodeVersion;
    private Integer invokeWaitTime;
    private Integer deployWaitTime;
    private String username;
}

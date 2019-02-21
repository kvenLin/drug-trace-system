package com.uchain.drugtracesystem.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: clf
 * @Date: 19-2-20
 * @Description:
 */
@Configuration
@ConfigurationProperties(value = "fabric")
@Data
public class ManagerConfig {
    private String channelName;
    private String version;
    private String ordererDomainName;
    private String orgName;
    private String orgMspId;
    private String orgDomainName;
}

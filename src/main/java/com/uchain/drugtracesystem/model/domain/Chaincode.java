package com.uchain.drugtracesystem.model.domain;

import java.io.Serializable;

public class Chaincode implements Serializable {
    private Long id;

    private String channelName;

    private String chaincodeName;

    private String chaincodePath;

    private String chaincodeVersion;

    private Integer invokeWaitTime;

    private Integer deployWaitTime;

    private Long createdBy;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChaincodeName() {
        return chaincodeName;
    }

    public void setChaincodeName(String chaincodeName) {
        this.chaincodeName = chaincodeName == null ? null : chaincodeName.trim();
    }

    public String getChaincodePath() {
        return chaincodePath;
    }

    public void setChaincodePath(String chaincodePath) {
        this.chaincodePath = chaincodePath == null ? null : chaincodePath.trim();
    }

    public String getChaincodeVersion() {
        return chaincodeVersion;
    }

    public void setChaincodeVersion(String chaincodeVersion) {
        this.chaincodeVersion = chaincodeVersion == null ? null : chaincodeVersion.trim();
    }

    public Integer getInvokeWaitTime() {
        return invokeWaitTime;
    }

    public void setInvokeWaitTime(Integer invokeWaitTime) {
        this.invokeWaitTime = invokeWaitTime;
    }

    public Integer getDeployWaitTime() {
        return deployWaitTime;
    }

    public void setDeployWaitTime(Integer deployWaitTime) {
        this.deployWaitTime = deployWaitTime;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", channelName=").append(channelName);
        sb.append(", chaincodeName=").append(chaincodeName);
        sb.append(", chaincodePath=").append(chaincodePath);
        sb.append(", chaincodeVersion=").append(chaincodeVersion);
        sb.append(", invokeWaitTime=").append(invokeWaitTime);
        sb.append(", deployWaitTime=").append(deployWaitTime);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
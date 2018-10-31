package com.uchain.drugtracesystem.fabric.bean;


/**
 * Fabric创建的chaincode信息，涵盖所属channel等信息
 */
public class Chaincode {
    /** 当前将要访问的智能合约所属频道名称 */
    private String channelName;
    /** 智能合约名称 */
    private String chaincodeName;
    /** 智能合约安装路径 */
    private String chaincodePath;
    /** 智能合约版本号 */
    private String chaincodeVersion;
    /** 执行智能合约操作等待时间 */
    private int invokeWaitTime = 100000;
    /** 执行智能合约实例等待时间 */
    private int deployWaitTime = 120000;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChaincodeName() {
        return chaincodeName;
    }

    public void setChaincodeName(String chaincodeName) {
        this.chaincodeName = chaincodeName;
    }

    public String getChaincodePath() {
        return chaincodePath;
    }

    public void setChaincodePath(String chaincodePath) {
        this.chaincodePath = chaincodePath;
    }

    public String getChaincodeVersion() {
        return chaincodeVersion;
    }

    public void setChaincodeVersion(String chaincodeVersion) {
        this.chaincodeVersion = chaincodeVersion;
    }

    public int getInvokeWaitTime() {
        return invokeWaitTime;
    }

    public void setInvokeWaitTime(int invokeWaitTime) {
        this.invokeWaitTime = invokeWaitTime;
    }

    public int getDeployWaitTime() {
        return deployWaitTime;
    }

    public void setDeployWaitTime(int deployWaitTime) {
        this.deployWaitTime = deployWaitTime;
    }
}

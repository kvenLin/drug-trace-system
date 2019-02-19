package com.uchain.drugtracesystem.form.fabric;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: clf
 * @Date: 19-2-17
 * @Description:
 */
@Data
public class PeerForm {
    @NotNull(message = "节点名不能为空")
    private String peerName;
    @NotNull(message = "事件监听节点名称不能为空")
    private String peerEventHubName;
    @NotNull(message = "ip地址不能为空")
    private String peerLocation;
    @NotNull(message = "事件监听节点ip地址不能为空")
    private String peerEventHubLocation;
    @NotNull(message = "ca节点监听地址不能为空")
    private String caLocation;
}

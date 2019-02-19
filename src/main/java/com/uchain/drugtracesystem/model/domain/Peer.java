package com.uchain.drugtracesystem.model.domain;

import java.io.Serializable;

public class Peer implements Serializable {
    private Long id;

    private String peerName;

    private String peerEventHubName;

    private String peerLocation;

    private String peerEventHubLocation;

    private String caLocation;

    private Integer addEventHub;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeerName() {
        return peerName;
    }

    public void setPeerName(String peerName) {
        this.peerName = peerName == null ? null : peerName.trim();
    }

    public String getPeerEventHubName() {
        return peerEventHubName;
    }

    public void setPeerEventHubName(String peerEventHubName) {
        this.peerEventHubName = peerEventHubName == null ? null : peerEventHubName.trim();
    }

    public String getPeerLocation() {
        return peerLocation;
    }

    public void setPeerLocation(String peerLocation) {
        this.peerLocation = peerLocation == null ? null : peerLocation.trim();
    }

    public String getPeerEventHubLocation() {
        return peerEventHubLocation;
    }

    public void setPeerEventHubLocation(String peerEventHubLocation) {
        this.peerEventHubLocation = peerEventHubLocation == null ? null : peerEventHubLocation.trim();
    }

    public String getCaLocation() {
        return caLocation;
    }

    public void setCaLocation(String caLocation) {
        this.caLocation = caLocation == null ? null : caLocation.trim();
    }

    public Integer getAddEventHub() {
        return addEventHub;
    }

    public void setAddEventHub(Integer addEventHub) {
        this.addEventHub = addEventHub;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", peerName=").append(peerName);
        sb.append(", peerEventHubName=").append(peerEventHubName);
        sb.append(", peerLocation=").append(peerLocation);
        sb.append(", peerEventHubLocation=").append(peerEventHubLocation);
        sb.append(", caLocation=").append(caLocation);
        sb.append(", addEventHub=").append(addEventHub);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
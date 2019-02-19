package com.uchain.drugtracesystem.model.domain;

import java.io.Serializable;

public class Orderer implements Serializable {
    private Long id;

    private String ordererName;

    private String ordererLocation;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdererName() {
        return ordererName;
    }

    public void setOrdererName(String ordererName) {
        this.ordererName = ordererName == null ? null : ordererName.trim();
    }

    public String getOrdererLocation() {
        return ordererLocation;
    }

    public void setOrdererLocation(String ordererLocation) {
        this.ordererLocation = ordererLocation == null ? null : ordererLocation.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ordererName=").append(ordererName);
        sb.append(", ordererLocation=").append(ordererLocation);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
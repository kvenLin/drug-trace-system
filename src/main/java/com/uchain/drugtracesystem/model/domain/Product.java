package com.uchain.drugtracesystem.model.domain;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private Long id;

    private Integer sequence;

    private Integer batch;

    private Long drugId;

    private Date createTime;

    private Date exp;

    private Long buyerId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sequence=").append(sequence);
        sb.append(", batch=").append(batch);
        sb.append(", drugId=").append(drugId);
        sb.append(", createTime=").append(createTime);
        sb.append(", exp=").append(exp);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
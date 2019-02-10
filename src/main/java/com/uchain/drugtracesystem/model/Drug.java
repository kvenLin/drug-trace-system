package com.uchain.drugtracesystem.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Drug {
    private String id;
    private String ownerId;
    private String name;
    private BigDecimal price;

    private String fileHash;
    private String description;
    private String buyer;
    private List<Trace> traces;
}

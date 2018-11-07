package com.uchain.drugtracesystem.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    public User() {
        this.role = "user";
        this.balance = BigDecimal.valueOf(100);
        this.crePoint = 0;
    }

    private String id;
    private String password;
    private String role;
    private BigDecimal balance;
    private Integer crePoint;
}

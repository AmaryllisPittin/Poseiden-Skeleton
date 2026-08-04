package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer TradeId;

    @Column(nullable = false, length = 30)
    private String account;

    @Column(nullable = false, length = 30)
    private String type;

    private Double buyQuantity;
    private Double sellQuantity;
    private Double buyPrice;
    private Double sellPrice;
    private LocalDateTime tradeDate;
    private String security;
    private String status;
    private String trader;
    private String benchmark;
    private String book;
    private String creationName;
    private LocalDateTime creationDate;
    private String revisionName;
    private LocalDateTime revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }
}

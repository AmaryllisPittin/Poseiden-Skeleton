package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bidlist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId")
    private Integer bidListId;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Account is mandatory")
    private String account;

    @Column(nullable = false, length = 30)
    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotNull(message = "Bid Quantity is mandatory")
    @Positive(message = "Bid Quantity must be greater than zero")
    private Double bidQuantity;

    private Double askQuantity;
    private Double bid;
    private Double ask;
    private String benchmark;
    private LocalDateTime bidListDate;
    private String commentary;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;
    private LocalDateTime creationDate;
    private String revisionName;
    private LocalDateTime revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;

    public BidList(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

}
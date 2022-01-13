package com.codecool.codecoin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private LocalDateTime date;
    private TransactionType type;
    private String cryptoId;
    //private Cryptocurrency crypto;
    private BigDecimal cryptoAmount;
    private CurrencyType currency;
    private BigDecimal currencyAmount;
}

package com.codecool.codecoin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "transaction_sequence"
    )
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;
    private Long userId;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String cryptoId;
    //private Cryptocurrency crypto;
    private BigDecimal cryptoAmount;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    private BigDecimal currencyAmount;
}

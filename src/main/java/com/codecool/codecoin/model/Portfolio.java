package com.codecool.codecoin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
public class Portfolio {
    @Id
    @SequenceGenerator(
            name = "portfolio_sequence",
            sequenceName = "portfolio_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "portfolio_sequence"
    )
    @Column(
            name = "id",
            nullable = false
    )
    private Long id;
    @ElementCollection
    @MapKeyColumn(
            name = "cryptoId"
    )
    @Column(
            name = "amount"
    )
    @CollectionTable(
            name = "crypto_currencies"
    )
    private final Map<String, BigDecimal> cryptoCurrencies;

    public Portfolio() {
        cryptoCurrencies = new HashMap<>();
    }
}

package com.codecool.codecoin.model;

import lombok.Getter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
public class Portfolio {
    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private final Map<String, BigDecimal> cryptoCurrencies;

    public Portfolio() {
        cryptoCurrencies = new HashMap<>();
    }
}

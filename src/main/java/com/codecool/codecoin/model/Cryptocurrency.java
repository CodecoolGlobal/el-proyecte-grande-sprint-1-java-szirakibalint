package com.codecool.codecoin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <pre>
 * Stores relevant information about a cryptocurrency.
 * Fields are populated with data fetched from a third party API.
 * JsonProperty annotations are used to make field names
 * match the incoming data from the API.
 * Contains getters to return data from fields for JSON conversion.
 * </pre>
 */
@Getter
@JsonSerialize
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Cryptocurrency {
    private String id;
    private String symbol;
    private String name;
    private String image;
    private Integer marketCapRank;
    private BigDecimal currentPrice;
    private LocalDateTime lastUpdated;
    private BigDecimal amount;
    @JsonProperty("price_change_24h")
    private BigDecimal priceChange24h;
    @JsonProperty("price_change_percentage_24h")
    private BigDecimal priceChangePercentage24h;
    @JsonProperty("high_24h")
    private BigDecimal high24h;
    @JsonProperty("low_24h")
    private BigDecimal low24h;

    /**
     * <pre>
     * Instances are compared based on their marketCapRank
     * which is a unique integer.
     * </pre>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cryptocurrency that = (Cryptocurrency) o;
        return marketCapRank.equals(that.marketCapRank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marketCapRank);
    }


    @Override
    public String toString() {
        return name;
    }
}

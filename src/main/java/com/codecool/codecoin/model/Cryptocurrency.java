package com.codecool.codecoin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonSerialize
public class Cryptocurrency {
    private String id;
    private String symbol;
    private String name;
    private String image;
    @JsonProperty("market_cap_rank")
    private Integer marketCapRank;
    @JsonProperty("current_price")
    private BigDecimal currentPrice;
    @JsonProperty("price_change_24h")
    private BigDecimal priceChange24h;
    @JsonProperty("price_change_percentage_24h")
    private BigDecimal priceChangePercentage24h;
    @JsonProperty("high_24h")
    private BigDecimal high24h;
    @JsonProperty("low_24h")
    private BigDecimal low24h;
    @JsonProperty("last_updated")
    private LocalDateTime lastUpdated;
    private BigDecimal amount;

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

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Integer getMarketCapRank() {
        return marketCapRank;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public BigDecimal getPriceChange24h() {
        return priceChange24h;
    }

    public BigDecimal getPriceChangePercentage24h() {
        return priceChangePercentage24h;
    }

    public BigDecimal getHigh24h() {
        return high24h;
    }

    public BigDecimal getLow24h() {
        return low24h;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return name;
    }
}

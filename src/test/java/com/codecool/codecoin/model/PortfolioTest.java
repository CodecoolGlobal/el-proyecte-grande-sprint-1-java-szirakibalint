package com.codecool.codecoin.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PortfolioTest {

    private static final BigDecimal REALLY_SMALL_NUMBER = BigDecimal.valueOf(000000000.1);

    @Mock
    private Cryptocurrency mockCurrency;

    private Portfolio portfolio;

    @BeforeEach
    public void setup() {
        portfolio = new Portfolio();
        Mockito.when(mockCurrency.getCurrentPrice()).thenReturn(BigDecimal.valueOf(10000));
    }

    @Test
    public void buyCrypto_ValidNumber_TransactionHappens() {
        assertTrue(portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2)));
    }

    @Test
    public void buyCrypto_ValidNumber_CryptoStoredInPortfolio() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        assertEquals(BigDecimal.valueOf(2), portfolio.getCryptoCurrencies().get(mockCurrency));
    }

    @Test
    public void buyCrypto_ValidNumberTwice_CryptoStoredInPortfolio() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.ONE);
        portfolio.buyCrypto(mockCurrency, BigDecimal.ONE);
        assertEquals(BigDecimal.valueOf(2), portfolio.getCryptoCurrencies().get(mockCurrency));
    }

    @Test
    public void buyCrypto_ValidNumber_PriceOfTheCryptoRemoved() {
        BigDecimal expectedValue = portfolio.getCurrencies().get(CurrencyType.USD).subtract(mockCurrency.getCurrentPrice());
        portfolio.buyCrypto(mockCurrency, BigDecimal.ONE);
        assertEquals(expectedValue, portfolio.getCurrencies().get(CurrencyType.USD));
    }

    @Test
    public void buyCrypto_NotEnoughMoney_TransactionNotHappens() {
        assertFalse(portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(3)));
    }

    @Test
    public void buyCrypto_NotEnoughMoney_CryptoNotAddedToPortfolio() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(3));
        assertEquals(BigDecimal.ZERO, portfolio.getCryptoCurrencies().getOrDefault(mockCurrency, BigDecimal.ZERO));
    }

    @Test
    public void buyCrypto_NotEnoughMoney_PriceOfTheCryptoNotRemoved() {
        BigDecimal expectedValue = portfolio.getCurrencies().get(CurrencyType.USD);
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(3));
        assertEquals(expectedValue, portfolio.getCurrencies().get(CurrencyType.USD));
    }

    @Test
    public void buyCrypto_AmountIsZero_TransactionNotHappens() {
        assertFalse(portfolio.buyCrypto(mockCurrency, BigDecimal.ZERO));
    }

    @Test
    public void buyCrypto_AmountIsNegative_TransactionNotHappens() {
        assertFalse(portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(-1)));
    }

    @Test
    public void buyCrypto_AmountIsNegative_CryptoNotAddedToPortfolio() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(-1));
        assertEquals(BigDecimal.ZERO, portfolio.getCryptoCurrencies().getOrDefault(mockCurrency, BigDecimal.ZERO));
    }

    @Test
    public void buyCrypto_AmountIsNegative_PriceOfTheCryptoNotRemoved() {
        BigDecimal expectedValue = portfolio.getCurrencies().get(CurrencyType.USD);
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(-1));
        assertEquals(expectedValue, portfolio.getCurrencies().get(CurrencyType.USD));
    }

    @Test
    public void buyCrypto_ReallySmallNumber_TransactionHappens() {
        assertTrue(portfolio.buyCrypto(mockCurrency, REALLY_SMALL_NUMBER));
    }

    @Test
    public void buyCrypto_ReallySmallNumber_CryptoStoredInPortfolio() {
        portfolio.buyCrypto(mockCurrency, REALLY_SMALL_NUMBER);
        assertEquals(REALLY_SMALL_NUMBER, portfolio.getCryptoCurrencies().get(mockCurrency));
    }

    @Test
    public void buyCrypto_ReallySmallNumber_PriceOfTheCryptoRemoved() {
        BigDecimal expectedValue = portfolio.getCurrencies().get(CurrencyType.USD).subtract(mockCurrency.getCurrentPrice().multiply(REALLY_SMALL_NUMBER));
        portfolio.buyCrypto(mockCurrency, REALLY_SMALL_NUMBER);
        assertEquals(expectedValue, portfolio.getCurrencies().get(CurrencyType.USD));
    }

    @Test
    public void sellCrypto_ValidAmount_TransactionHappens() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        assertTrue(portfolio.sellCrypto(mockCurrency, BigDecimal.valueOf(2)));
    }

    @Test
    public void sellCrypto_ValidAmount_CryptoRemovedFromPortfolio() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        portfolio.sellCrypto(mockCurrency, BigDecimal.valueOf(2));
        assertEquals(BigDecimal.ZERO, portfolio.getCryptoCurrencies().get(mockCurrency));
    }

    @Test
    public void sellCrypto_ValidAmount_MoneyAddedToWallet() {
        BigDecimal expectedValue = portfolio.getCurrencies().get(CurrencyType.USD);
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        portfolio.sellCrypto(mockCurrency, BigDecimal.valueOf(2));
        assertEquals(expectedValue, portfolio.getCurrencies().get(CurrencyType.USD));
    }

    @Test
    public void sellCrypto_NotEnoughCrypto_TransactionNotHappens() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        assertFalse(portfolio.sellCrypto(mockCurrency, BigDecimal.valueOf(3)));
    }

    @Test
    public void sellCrypto_NotEnoughCrypto_CryptoNotRemovedFromPortfolio() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        portfolio.sellCrypto(mockCurrency, BigDecimal.valueOf(3));
        assertEquals(BigDecimal.valueOf(2), portfolio.getCryptoCurrencies().get(mockCurrency));
    }

    @Test
    public void sellCrypto_NotEnoughCrypto_MoneyNotAddedToWallet() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        BigDecimal expectedValue = portfolio.getCurrencies().get(CurrencyType.USD);
        portfolio.sellCrypto(mockCurrency, BigDecimal.valueOf(3));
        assertEquals(expectedValue, portfolio.getCurrencies().get(CurrencyType.USD));
    }

    @Test
    public void sellCrypto_AmountIsZero_TransactionNotHappens() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        assertFalse(portfolio.sellCrypto(mockCurrency, BigDecimal.ZERO));
    }

    @Test
    public void sellCrypto_AmountIsNegative_TransactionNotHappens() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        assertFalse(portfolio.sellCrypto(mockCurrency, BigDecimal.valueOf(-1)));
    }

    @Test
    public void sellCrypto_AmountIsNegative_CryptoNotRemovedFromPortfolio() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        portfolio.sellCrypto(mockCurrency, BigDecimal.valueOf(-1));
        assertEquals(BigDecimal.valueOf(2), portfolio.getCryptoCurrencies().get(mockCurrency));
    }

    @Test
    public void sellCrypto_AmountIsNegative_MoneyNotAddedToWallet() {
        portfolio.buyCrypto(mockCurrency, BigDecimal.valueOf(2));
        BigDecimal expectedValue = portfolio.getCurrencies().get(CurrencyType.USD);
        portfolio.sellCrypto(mockCurrency, BigDecimal.valueOf(-1));
        assertEquals(expectedValue, portfolio.getCurrencies().get(CurrencyType.USD));
    }

    @Test
    public void sellCrypto_ReallySmallNumber_TransactionHappens() {
        portfolio.buyCrypto(mockCurrency, REALLY_SMALL_NUMBER);
        assertTrue(portfolio.sellCrypto(mockCurrency, REALLY_SMALL_NUMBER));
    }

    @Test
    public void sellCrypto_ReallySmallNumber_CryptoStoredInPortfolio() {
        portfolio.buyCrypto(mockCurrency, REALLY_SMALL_NUMBER);
        portfolio.sellCrypto(mockCurrency, REALLY_SMALL_NUMBER);
        assertEquals(0, BigDecimal.ZERO.compareTo(portfolio.getCryptoCurrencies().get(mockCurrency)));
    }

    @Test
    public void sellCrypto_ReallySmallNumber_PriceOfTheCryptoRemoved() {
        BigDecimal expectedValue = portfolio.getCurrencies().get(CurrencyType.USD);
        portfolio.buyCrypto(mockCurrency, REALLY_SMALL_NUMBER);
        portfolio.sellCrypto(mockCurrency, REALLY_SMALL_NUMBER);
        assertEquals(0, expectedValue.compareTo(portfolio.getCurrencies().get(CurrencyType.USD)));
    }
}
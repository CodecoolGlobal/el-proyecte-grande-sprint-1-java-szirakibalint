package com.codecool.codecoin.service;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.dao.UserDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.CurrencyType;
import com.codecool.codecoin.model.Portfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CalculatorTest {

    @Mock
    private CryptocurrencyDAO mockCryptocurrencyDAO;

    @Mock
    private UserDAO mockUserDao;

    @InjectMocks
    private Calculator calculator;

    Portfolio mockfolio;
    Cryptocurrency mockCurrency;

    @BeforeEach
    public void setup() {
        mockfolio = Mockito.mock(Portfolio.class);
        mockCurrency = Mockito.mock(Cryptocurrency.class);
        Map<Cryptocurrency, BigDecimal> mockCryptoData = new HashMap<>(){{
            put(mockCurrency, BigDecimal.ONE);
        }};
        Map<CurrencyType, BigDecimal> mockData = new HashMap<>(){{
           put(CurrencyType.USD, BigDecimal.ZERO);
        }};
        Mockito.when(mockfolio.getCryptoCurrencies()).thenReturn(mockCryptoData);
        Mockito.when(mockfolio.getCurrencies()).thenReturn(mockData);
        Mockito.when(mockCurrency.getMarketCapRank()).thenReturn(0);
        Mockito.when(mockCurrency.getCurrentPrice()).thenReturn(BigDecimal.ZERO);
    }


    @Test
    public void calculateTotalBalance_NewPortfolio_ReturnsTwentyThousands() {
        Mockito.when(mockUserDao.getPortfolio()).thenReturn(new Portfolio());
        BigDecimal expectedValue = BigDecimal.valueOf(20000);
        assertEquals(expectedValue, calculator.calculateTotalBalance());
    }

    @Test
    public void calculateTotalBalance_MockPortfolio_ReturnsZero() {
        Mockito.when(mockUserDao.getPortfolio()).thenReturn(mockfolio);
        Mockito.when(mockCryptocurrencyDAO.getCurrencyById(Mockito.any())).thenReturn(mockCurrency);
        assertEquals(BigDecimal.ZERO, calculator.calculateTotalBalance());
    }

    @Test
    public void calculateTotalBalance_MockPortfolioHasOneCryptos_DAOGetMethodCalledOneTimes() {
        Mockito.when(mockUserDao.getPortfolio()).thenReturn(mockfolio);
        Mockito.when(mockCryptocurrencyDAO.getCurrencyById(Mockito.any())).thenReturn(mockCurrency);
        calculator.calculateTotalBalance();
        Mockito.verify(mockCryptocurrencyDAO, Mockito.times(1)).getCurrencyById(Mockito.any());
    }

    @Test
    public void calculateTotalBalance_MockPortfolioHundredWorthOfCryptos_ReturnsHundred() {
        Mockito.when(mockCurrency.getCurrentPrice()).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(mockUserDao.getPortfolio()).thenReturn(mockfolio);
        Mockito.when(mockCryptocurrencyDAO.getCurrencyById(Mockito.any())).thenReturn(mockCurrency);
        assertEquals(BigDecimal.valueOf(100), calculator.calculateTotalBalance());
    }
}
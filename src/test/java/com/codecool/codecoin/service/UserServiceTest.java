package com.codecool.codecoin.service;

import com.codecool.codecoin.dao.UserDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import com.codecool.codecoin.model.Portfolio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private UserDAO mockUserDao;

    @Mock
    private Calculator calculator;

    @Mock
    private Portfolio mockfolio;

    @Mock
    private Cryptocurrency mockCurrency;

    @InjectMocks
    private UserService userService;

    @Test
    public void getPortfolio_InjectMockfolio_ReturnsMockfolio() {
        Mockito.when(mockUserDao.getPortfolio()).thenReturn(mockfolio);
        assertEquals(mockfolio, userService.getPortfolio());
    }

    @Test
    public void buyCrypto_BuyCryptoOnce_MethodInvokedOneTime() {
        Mockito.when(mockUserDao.buyCrypto(Mockito.anyString(), Mockito.any(BigDecimal.class))).thenReturn(Mockito.anyString(), Mockito.anyString());
        userService.buyCryptocurrency("id", BigDecimal.ONE);
        Mockito.verify(mockUserDao, Mockito.times(1)).buyCrypto(Mockito.anyString(), Mockito.any(BigDecimal.class));
    }

    @Test
    public void sellCrypto_BuyCryptoOnce_MethodInvokedOneTime() {
        Mockito.when(mockUserDao.sellCrypto(Mockito.anyString(), Mockito.any(BigDecimal.class))).thenReturn(Mockito.anyString(), Mockito.anyString());
        userService.sellCryptocurrency("id", BigDecimal.ONE);
        Mockito.verify(mockUserDao, Mockito.times(1)).sellCrypto(Mockito.anyString(), Mockito.any(BigDecimal.class));
    }

    @Test
    public void getTotalBalance_UserDAOReturnsZero_ReturnsZero() {
        Mockito.when(calculator.calculateTotalBalance()).thenReturn(BigDecimal.ZERO);
        assertEquals(BigDecimal.ZERO, userService.getTotalBalance());
    }

}
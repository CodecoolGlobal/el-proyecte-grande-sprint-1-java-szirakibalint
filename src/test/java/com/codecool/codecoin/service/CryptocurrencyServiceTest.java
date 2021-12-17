package com.codecool.codecoin.service;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CryptocurrencyServiceTest {

    @Mock
    private CryptocurrencyDAO mockCryptocurrencyDAO;

    @InjectMocks
    private CryptocurrencyService cryptocurrencyService;

    @Test
    public void getAll_CryptoDAOReturnsEmptySet_CryptoDAOGetMethodCalledOnce() {
        Mockito.when(mockCryptocurrencyDAO.getAll()).thenReturn(new HashSet<>());
        cryptocurrencyService.getAll();
        Mockito.verify(mockCryptocurrencyDAO, Mockito.times(1)).getAll();
    }

    @Test
    public void getAll_CryptoDAOReturnsEmptySet_ServiceReturnsEmptySet() {
        Mockito.when(mockCryptocurrencyDAO.getAll()).thenReturn(new HashSet<>());
        assertEquals(cryptocurrencyService.getAll(), new HashSet<>());
    }

    @Test
    public void getAll_CryptoDAOReturnsValidData_ServiceReturnsSameData() {
        Set<Cryptocurrency> testData = new HashSet<>() {{
            add(new Cryptocurrency());
        }};
        Mockito.when(mockCryptocurrencyDAO.getAll()).thenReturn(testData);
        assertEquals(cryptocurrencyService.getAll(), testData);
    }

    @Test
    public void getCryptoById_CryptoDAOReturnsNull_CryptoDAOGetMethodCalledOnce() {
        Mockito.when(mockCryptocurrencyDAO.getCurrencyById(Mockito.any())).thenReturn(null);
        cryptocurrencyService.getCurrencyById("id");
        Mockito.verify(mockCryptocurrencyDAO, Mockito.times(1)).getCurrencyById("id");
    }

    @Test
    public void getCryptoById_CryptoDAOReturnsNull_ServiceReturnsNull() {
        Mockito.when(mockCryptocurrencyDAO.getCurrencyById(Mockito.any())).thenReturn(null);
        assertNull(cryptocurrencyService.getCurrencyById("id"));
    }

    @Test
    public void getCryptoById_CryptoDAOReturnsValidCrypto_ServiceReturnsSameCrypto() {
        Cryptocurrency validCrypto = new Cryptocurrency();
        Mockito.when(mockCryptocurrencyDAO.getCurrencyById(Mockito.any())).thenReturn(validCrypto);
        assertEquals(cryptocurrencyService.getCurrencyById("id"), validCrypto);
    }

}
package com.codecool.codecoin.dao.implementation;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Set;

/**
 * Implements Cryptocurrency data access methods.
 */
@Service
public class CryptocurrencyDAOAPI implements CryptocurrencyDAO {
    private WebClient webClient;

    /**
     * Sets the webclient for the DAO.
     * @param webClient used to fetch data from the third party API.
     */
    @Autowired
    private void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * Fetches all Cryptocurrencies from the third party API.
     * @return Set of all fetched Cryptocurrencies.
     */
    @Override
    public Set<Cryptocurrency> getAll() {
        return webClient.get()
                .uri("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Set<Cryptocurrency>>() {})
                .block();
    }

    /**
     * Fetches a Cryptocurrency from the third party API by a given id.
     * @param id an identifier string for a Cryptocurrency (e.g. "bitcoin")
     * @return Cryptocurrency object if a match is found, else null.
     */
    @Override
    public Cryptocurrency getCurrencyById(String id) {
        List<Cryptocurrency> data = webClient.get()
                .uri("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Cryptocurrency>>() {})
                .block();
        return (data != null) && (data.size() == 1) ? data.get(0) : null;
    }
}

package com.codecool.codecoin.dao.implementation;

import com.codecool.codecoin.dao.CryptocurrencyDAO;
import com.codecool.codecoin.model.Cryptocurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Set;

@Service
public class CryptocurrencyDAOAPI implements CryptocurrencyDAO {
    private WebClient webClient;

    @Autowired
    private void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Set<Cryptocurrency> getAll() {
        return webClient.get()
                .uri("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Set<Cryptocurrency>>() {})
                .block();
    }

    @Override
    public Cryptocurrency get(String id) {
        return null;
    }
}

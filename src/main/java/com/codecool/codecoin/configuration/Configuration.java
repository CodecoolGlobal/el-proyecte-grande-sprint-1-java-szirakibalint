package com.codecool.codecoin.configuration;

import com.codecool.codecoin.logic.Calculator;
import com.codecool.codecoin.model.Portfolio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public WebClient getWebClient() {
        return WebClient.create();
    }

    @Bean
    public Portfolio getPortfolio() {
        return Portfolio.getInstance();
    }

    @Bean
    public Calculator getCalculator() {
        return new Calculator();
    }
}

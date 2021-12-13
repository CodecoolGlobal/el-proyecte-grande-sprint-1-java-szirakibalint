package com.codecool.codecoin.configuration;

import com.codecool.codecoin.service.Calculator;
import com.codecool.codecoin.model.Portfolio;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Contains beans used for autowiring.
 */
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

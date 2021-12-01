package com.codecool.codecoin.configuration;

import com.codecool.codecoin.model.Portfolio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
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
    @Scope("singleton")
    public Portfolio getPortfolio() {
        return Portfolio.getInstance();
    }
}

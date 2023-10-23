package com.devsu.account.config;

import com.devsu.account.config.properties.ClientServiceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Bean
    @ConfigurationProperties(prefix = "com.devsu.clients")
    ClientServiceProperties clientServiceProperties() {
        return new ClientServiceProperties();
    }

}

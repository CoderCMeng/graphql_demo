package com.czy.grphql_demo.config;

import com.czy.grphql_demo.config.cors.RegexCorsConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    @ConfigurationProperties("cors")
    public RegexCorsConfiguration regexCorsConfiguration() {
        return new RegexCorsConfiguration();
    }
}

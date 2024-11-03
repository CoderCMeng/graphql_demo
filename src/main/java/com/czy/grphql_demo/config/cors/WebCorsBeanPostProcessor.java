package com.czy.grphql_demo.config.cors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class WebCorsBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private RegexCorsConfiguration corsConfiguration;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.getClass().isAssignableFrom(CorsFilter.class)) {
            return bean;
        }
        Map<String, CorsConfiguration> corsConfigurations = new LinkedHashMap<>();
        corsConfiguration.applyPermitDefaultValues();
        corsConfigurations.put("/graphql/**", corsConfiguration);
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.setCorsConfigurations(corsConfigurations);
        configurationSource.setAlwaysUseFullPath(true);
        return new CorsFilter(configurationSource);
    }
}


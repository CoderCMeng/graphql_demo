package com.czy.grphql_demo.config.cors;

import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

public class RegexCorsConfiguration extends CorsConfiguration {
    private AllowedOrigins allowedOrigins;

    @Override
    public void setAllowedOrigins(List<String> allowedOrigins) {
        super.setAllowedOrigins(allowedOrigins);
        this.allowedOrigins = new AllowedOrigins(allowedOrigins);
    }

    @Override
    public String checkOrigin(String requestOrigin) {
        if (!StringUtils.hasText(requestOrigin)) {
            return null;
        }
        if (allowedOrigins.isEmpty()) {
            return null;
        }
        Boolean allowCredentials = getAllowCredentials();
        if (allowedOrigins.hasAll()) {
            if (allowCredentials != Boolean.TRUE) {
                return ALL;
            } else {
                return requestOrigin;
            }
        }
        return allowedOrigins.checkOrigin(requestOrigin);
    }
}

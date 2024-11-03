package com.czy.grphql_demo.config.cors;

import org.springframework.web.cors.CorsConfiguration;

import java.util.*;
import java.util.regex.Pattern;

public class AllowedOrigins {
    private static final Pattern WILDCARD_PATTERN = Pattern.compile("^\\*\\.\\S+\\.\\S+$");
    private boolean hasAll;
    private List<AllowedOrigin> allowedOrigins;

    public AllowedOrigins(List<String> allowedOrigins) {
        init(new HashSet<>(allowedOrigins));
    }

    private void init(Set<String> allowedOrigins) {
        this.allowedOrigins = new ArrayList<>();
        allowedOrigins.forEach(allowedOrigin -> {
            if (Objects.equals(CorsConfiguration.ALL, allowedOrigin)) {
                this.hasAll = true;
            } else if (WILDCARD_PATTERN.matcher(allowedOrigin).matches()) {
                this.allowedOrigins.add(new WildcardAllowedOrigin(allowedOrigin));
            } else {
                this.allowedOrigins.add(new FixedAllowedOrigin(allowedOrigin));
            }
        });
    }

    public boolean isEmpty() {
        return !hasAll && allowedOrigins.isEmpty();
    }

    public boolean hasAll() {
        return this.hasAll;
    }

    public String checkOrigin(String requestOrigin) {
        boolean matches = allowedOrigins.stream()
                .anyMatch(allowedOrigin -> allowedOrigin.matches(requestOrigin));
        return matches ? requestOrigin : null;
    }


    private interface AllowedOrigin {
        boolean matches(String requestOrigin);
    }

    private final class FixedAllowedOrigin implements AllowedOrigin {
        private final String domain;

        private FixedAllowedOrigin(String domain) {
            this.domain = domain;
        }

        @Override
        public boolean matches(String requestOrigin) {
            return Objects.equals(domain, requestOrigin);
        }
    }

    private final class WildcardAllowedOrigin implements AllowedOrigin {
        private final String mainDomain;

        private WildcardAllowedOrigin(String domain) {
            this.mainDomain = domain.substring(1);
        }

        @Override
        public boolean matches(String requestOrigin) {
            return requestOrigin.contains(mainDomain);
        }
    }

}

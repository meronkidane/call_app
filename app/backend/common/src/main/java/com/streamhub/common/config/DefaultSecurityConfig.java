package com.streamhub.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class DefaultSecurityConfig {

    @Value("${security.mock-auth.enabled:true}")
    private boolean mockAuthEnabled;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> {
                    exchange.pathMatchers("/actuator/**", "/*_status", "/_status", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                            .permitAll();
                    if (mockAuthEnabled) {
                        exchange.anyExchange().permitAll();
                    } else {
                        exchange.anyExchange().authenticated();
                    }
                })
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .logout(ServerHttpSecurity.LogoutSpec::disable);

        if (!mockAuthEnabled) {
            http.oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
        }

        return http.build();
    }
}

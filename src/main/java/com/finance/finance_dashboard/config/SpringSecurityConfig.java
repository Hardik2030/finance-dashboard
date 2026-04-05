package com.finance.finance_dashboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth


                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()


                        .requestMatchers("/records/dashboard/**")
                        .hasAnyRole("ANALYST", "ADMIN")


                        .requestMatchers(HttpMethod.GET, "/records/**")
                        .hasAnyRole("VIEWER", "ANALYST", "ADMIN")


                        .requestMatchers(HttpMethod.POST, "/records/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/records/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/records/**")
                        .hasRole("ADMIN")


                        .requestMatchers("/users/**")
                        .hasRole("ADMIN")


                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {});

        return http.build();
    }
}
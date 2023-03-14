package com.guavapay.testtask.config;

import com.guavapay.testtask.security.jwt.JwtConfigurer;
import com.guavapay.testtask.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig{
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final String AdminEndpointPath = "/api/v1/admin";
    private final String CourierEndpointPath = "/api/v1/courier";
    private final String UserEndpointPath = "/api/v1/user";

    private String AuthEndpointSuffix = "/auth";
    private String OtherEndpointsSuffix = "/**";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(AdminEndpointPath+AuthEndpointSuffix).permitAll()
                .requestMatchers(CourierEndpointPath+AuthEndpointSuffix).permitAll()
                .requestMatchers(UserEndpointPath+AuthEndpointSuffix).permitAll()
                .requestMatchers(AdminEndpointPath+OtherEndpointsSuffix).hasRole("admin")
                .requestMatchers(CourierEndpointPath+OtherEndpointsSuffix).hasRole("courier")
                .requestMatchers(UserEndpointPath+OtherEndpointsSuffix).hasRole("user")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer());
        return http.build();
    }


}

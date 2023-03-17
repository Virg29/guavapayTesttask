package com.guavapay.testtask.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
	private ApplicationContext context;

    private final String AdminEndpointPath = "/api/v1/admin";
    private final String CourierEndpointPath = "/api/v1/courier";
    private final String UserEndpointPath = "/api/v1/user";

    private String AuthEndpointPath = "/api/v1/auth";
    private String OtherEndpointsSuffix = "/**";



    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = context.getBean(JwtAuthenticationFilter.class);
        http
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/actuator/**").permitAll()
            .requestMatchers("/swagger-ui/**").permitAll()
            .requestMatchers("/api-docs/**").permitAll()
            .requestMatchers(AuthEndpointPath+OtherEndpointsSuffix).permitAll()
            .requestMatchers(AdminEndpointPath+OtherEndpointsSuffix).hasAuthority("admin")
            .requestMatchers(CourierEndpointPath+OtherEndpointsSuffix).hasAuthority("courier")
            .requestMatchers(UserEndpointPath+OtherEndpointsSuffix).hasAuthority("user");
//            .requestMatchers(AdminEndpointPath+OtherEndpointsSuffix).permitAll()
//            .requestMatchers(CourierEndpointPath+OtherEndpointsSuffix).permitAll()
//            .requestMatchers(UserEndpointPath+OtherEndpointsSuffix).permitAll();
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return  http.build();
    }

    @Bean
    public UserDetailsService userDetailsServiceBean(){
        return new JwtUserDetailsService();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    { return authenticationConfiguration.getAuthenticationManager();}

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilterBean(){
        return new JwtAuthenticationFilter();
    }
    @Bean
    public JwtUtilities jwtUtilitiesBean(){
        return new JwtUtilities();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

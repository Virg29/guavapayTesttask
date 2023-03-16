package com.guavapay.testtask.config;

import com.guavapay.testtask.security.jwt.JwtTokenProvider;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.sql.DataSource;

@Configuration
public class Config {
    @Bean
    public DataSource dataSource() {
        String host = System.getenv("psqlHost")!=null?System.getenv("psqlHost"):"127.0.0.1";
        String port = System.getenv("psqlPort")!=null?System.getenv("psqlPort"):"5432";
        String db = System.getenv("psqlDB")!=null?System.getenv("psqlDB"):"app";
        String username = System.getenv("psqlUsername")!=null?System.getenv("psqlUsername"):"user";
        String password = System.getenv("psqlPassword")!=null?System.getenv("psqlPassword"):"password";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://%s:%s/%s".formatted(host,port,db));
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/db.changelog-master.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoderBean(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtTokenProvider jwtTokenProviderBean(){
        return new JwtTokenProvider();
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }
    @Bean
    @Autowired
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}

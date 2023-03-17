package com.guavapay.testtask.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

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
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    @Bean
//    public CommonsRequestLoggingFilter logFilter() {
//        CommonsRequestLoggingFilter filter
//                = new CommonsRequestLoggingFilter();
//        filter.setIncludeQueryString(true);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(10000);
//        filter.setIncludeHeaders(false);
//        filter.setAfterMessagePrefix("REQUEST DATA: ");
//        return filter;
//    }

}

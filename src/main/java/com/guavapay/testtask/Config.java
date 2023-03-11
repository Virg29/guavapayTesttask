package com.guavapay.testtask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
        String username = System.getenv("psqlUsername")!=null?System.getenv("psqlUsername"):"login";
        String password = System.getenv("psqlPassword")!=null?System.getenv("psqlPassword"):"password";

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://%s:%s/%s".formatted(host,port,db));
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
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

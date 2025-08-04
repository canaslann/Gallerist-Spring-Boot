package com.canaslan.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.canaslan"})
@ComponentScan(basePackages = {"com.canaslan"})
@EnableJpaRepositories(basePackages = "com.canaslan")
@SpringBootApplication
public class GalleristApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleristApplication.class, args);
    }

}

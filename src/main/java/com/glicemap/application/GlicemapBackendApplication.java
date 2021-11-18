package com.glicemap.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.glicemap")
@EnableJpaRepositories(basePackages={"com.glicemap.repository"})
@EntityScan("com.glicemap.model")
public class GlicemapBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlicemapBackendApplication.class, args);
    }

}

package com.glicemap.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.glicemap")
public class GlicemapBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlicemapBackendApplication.class, args);
    }

}

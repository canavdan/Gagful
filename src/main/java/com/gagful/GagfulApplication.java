package com.gagful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GagfulApplication {

    public static void main(String[] args) {
        SpringApplication.run(GagfulApplication.class, args);
    }

}

package com.learn.springbootguava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootGuavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGuavaApplication.class, args);
    }
}

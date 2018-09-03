package com.learn.springbootevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * http://www.importnew.com/26782.html
 * 详细说明
 */
@SpringBootApplication
@EnableAsync
public class SpringBootEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEventApplication.class, args);
    }


    /**
     * 定义线程异步任务ben
     *
     * @return
     */
    @Bean
    public Executor taskExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        return executorService;
    }
}

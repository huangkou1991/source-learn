package com.learn.nettyspringbootadmin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAdminServer
@Configuration
@EnableAutoConfiguration
public class NettySpringbootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettySpringbootAdminApplication.class, args);
    }
}

/**
 * 配置监控模块
 * http://codecentric.github.io/spring-boot-admin/1.5.6/
 */

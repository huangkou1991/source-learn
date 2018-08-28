package com.learn.nettyspringbootadmin;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
//@Configuration
//@EnableAutoConfiguration
public class NettySpringbootAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettySpringbootAdminApplication.class, args);
    }
}

/**
 * 配置监控模块
 */

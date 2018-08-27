package com.wade.springboothystrixlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 文章地址https://www.baeldung.com/introduction-to-hystrix
 * <p>
 * 提供对通常通过网络访问的服务的故障和延迟的保护和控制
 * 停止因某些服务停机而导致的故障级联
 * 快速失败并迅速恢复
 * 尽可能优雅地降级
 * 对故障进行实时监控和指挥中心警报
 */
@SpringBootApplication
public class SpringBootHystrixLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHystrixLearnApplication.class, args);
    }
}

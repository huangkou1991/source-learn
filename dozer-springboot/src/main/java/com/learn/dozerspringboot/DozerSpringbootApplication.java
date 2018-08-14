package com.learn.dozerspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * https://blog.csdn.net/u010832551/article/details/74518902
 * 数据存储与转换
 */
@SpringBootApplication
public class DozerSpringbootApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DozerSpringbootApplication.class, args);
        String[] name = context.getBeanDefinitionNames();
        List lists = Arrays.asList(name);
        lists.stream().forEach(a -> System.out.println(a));
    }
}

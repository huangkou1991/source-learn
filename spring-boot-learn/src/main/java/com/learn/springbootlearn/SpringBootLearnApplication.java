package com.learn.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootLearnApplication {

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(SpringBootLearnApplication.class, args);
        System.out.println(context);


        System.err.println(context.getBean("personFactoryBean")); //bean--person对象

        System.err.println(context.getBean("&personFactoryBean"));//beanFactory对象

    }
}

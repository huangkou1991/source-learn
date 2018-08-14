package com.learn.spring.cotainer.starter.beanpostprocessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :lwy
 * @date 2018/6/18 18:06
 */
public class PasswordTest {


    public static void main(String[] args) {

        ApplicationContext container = new ClassPathXmlApplicationContext("password.xml");

        PasswordDecodable passwordDecodable = (PasswordDecodable) container.getBean("password");
        System.out.println(passwordDecodable.getEncodedPassword());
    }
}

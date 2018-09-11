package com.learn;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author :lwy
 * @Date : 2018/9/11 18:56
 * @Description :
 */
public class HelloServiceTest {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"dubbo-server.xml"});
        context.start();

        System.in.read();
    }
}

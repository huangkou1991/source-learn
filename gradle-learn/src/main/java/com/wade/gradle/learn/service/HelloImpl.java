package com.wade.gradle.learn.service;

import org.springframework.stereotype.Service;

/**
 * @author :lwy
 * @date 2018/6/7 11:35
 */
@Service
public class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("hello.");
    }
}

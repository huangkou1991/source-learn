package com.wade.spring.learn.service.impl;

import com.wade.spring.learn.service.Hello;
import org.springframework.stereotype.Service;

/**
 * @author :lwy
 * @date 2018/6/6 18:55
 */
public class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}

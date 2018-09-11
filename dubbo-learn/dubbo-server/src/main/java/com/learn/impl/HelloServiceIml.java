package com.learn.impl;

import com.learn.HelloService;

/**
 * @Author :lwy
 * @Date : 2018/9/11 18:51
 * @Description :
 */
public class HelloServiceIml implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello,"+name;
    }
}

package com.learn.springbootguava.controller;

import com.learn.springbootguava.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :lwy
 * @Date : 2018/8/30 17:35
 * @Description :
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;


    @GetMapping("/hello")
    public String hello() {
        return helloService.test();
    }
}

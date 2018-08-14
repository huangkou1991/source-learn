package com.learn.springbootlearn.controller;

import com.learn.springbootlearn.aop.MethodAnnotation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :lwy
 * @date 2018/7/31 18:22
 */
@RestController
public class HelloController {



    @GetMapping("/hello")
    @ResponseBody
    @MethodAnnotation
    public String hello(){
        return "hello";
    }
}

package com.wade.springbookakka.service;

import org.springframework.stereotype.Service;

/**
 * @Author :lwy
 * @Date : 2018/8/24 11:13
 * @Description :
 */
@Service
public class GreetingService {
    public String greet(String name) {
        return "hello," + name;
    }

}

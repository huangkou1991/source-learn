package com.learn.springbootguava.service;

import org.springframework.stereotype.Service;

/**
 * @Author :lwy
 * @Date : 2018/8/30 17:34
 * @Description :
 */
@Service
public class HelloService {



    public String test(){
        JmxService service=new JmxService();
        service.getStackTrace();
        return "say hello";
    }
}

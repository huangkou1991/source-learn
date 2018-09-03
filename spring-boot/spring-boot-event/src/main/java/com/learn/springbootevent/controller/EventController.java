package com.learn.springbootevent.controller;

import com.learn.springbootevent.publish.DemoEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :lwy
 * @Date : 2018/9/3 15:35
 * @Description :
 */
@RestController
public class EventController {

    @Autowired
    private DemoEventPublisher demoEventPublisher;


    @GetMapping("/msg")
    public String sendMsg(){
        demoEventPublisher.publishMsg("msg");
        return "msg";
    }
}

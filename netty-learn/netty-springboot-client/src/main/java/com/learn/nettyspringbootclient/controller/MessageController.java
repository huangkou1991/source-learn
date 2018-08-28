package com.learn.nettyspringbootclient.controller;

import com.learn.nettyspringbootclient.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :lwy
 * @Date : 2018/8/28 14:45
 * @Description :
 */
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/msg")
    public void msg() {
        messageService.sendMsg();
    }

    //模拟TCP 拆、粘包？
    @GetMapping("/msg1")
    public void msg1() {
        for(int i=0;i<100;i++){
            messageService.sendMsg();
        }

    }
}

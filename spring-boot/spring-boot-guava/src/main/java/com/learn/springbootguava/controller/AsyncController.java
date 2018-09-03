package com.learn.springbootguava.controller;

import com.learn.springbootguava.service.AsyncClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :lwy
 * @Date : 2018/9/3 10:36
 * @Description :
 */
@RestController
public class AsyncController {


    @Autowired
    private AsyncClient asyncClient;

    @GetMapping("/async")
    public void async() {
        asyncClient.async();
    }
}

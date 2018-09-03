package com.learn.springbootasync.controller;

import com.learn.springbootasync.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @Author :lwy
 * @Date : 2018/9/3 11:12
 * @Description :
 */
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/void")
    public void testVoid() {
        asyncService.returnVoidType();
    }

    @GetMapping("/string")
    public void testString1() throws ExecutionException, InterruptedException {
        asyncService.returnStringType1();
    }

    @GetMapping("/string2")
    public String testString2() throws ExecutionException, InterruptedException {
        return asyncService.returnStringType2();
    }
}

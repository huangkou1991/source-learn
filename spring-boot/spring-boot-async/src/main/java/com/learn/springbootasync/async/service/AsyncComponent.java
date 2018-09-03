package com.learn.springbootasync.async.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Author :lwy
 * @Date : 2018/9/3 11:08
 * @Description :
 */
@Component
public class AsyncComponent {


    @Async
    public void asyncMethodWithVoidReturnType() {
        System.out.println("Execute method asynchronously. " + Thread.currentThread().getName());
    }

    @Async
    public Future<String> asyncMethodWithReturnType() {
        System.out.println("Execute method asynchronously " + Thread.currentThread().getName());
        try {
            //异步调用逻辑
            //Thread.sleep(5000);
            return new AsyncResult<>("hello world !!!!");
        } catch (Exception e) {

        }

        return null;
    }

}

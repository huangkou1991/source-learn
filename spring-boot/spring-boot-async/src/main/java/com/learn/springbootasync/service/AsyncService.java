package com.learn.springbootasync.service;

import com.learn.springbootasync.async.service.AsyncComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author :lwy
 * @Date : 2018/9/3 11:10
 * @Description :
 */
@Service
public class AsyncService {

    @Autowired
    private AsyncComponent asyncComponent;


    public void returnVoidType() {
        System.err.println("Start - invoking an asynchronous method. " + Thread.currentThread().getName());
        asyncComponent.asyncMethodWithVoidReturnType();
        System.err.println("End - invoking an asynchronous method. ");
    }

    public void returnStringType1() throws InterruptedException, ExecutionException {
        System.err.println("Start - invoking an asynchronous method. " + Thread.currentThread().getName());
        final Future<String> future = asyncComponent.asyncMethodWithReturnType();

        while (true) {
            if (future.isDone()) {
                System.err.println("Result from asynchronous process -                        " + future.get());
                break;
            }
            System.err.println("Continue doing something else. ");
            Thread.sleep(1000);
        }
    }

    public String returnStringType2() throws ExecutionException, InterruptedException {
        System.err.println("Start - invoking an asynchronous method. " + Thread.currentThread().getName());
        final Future<String> future = asyncComponent.asyncMethodWithReturnType();

        String result=future.get();
        System.err.println("execute success. " + Thread.currentThread().getName());
        return result;
    }
}

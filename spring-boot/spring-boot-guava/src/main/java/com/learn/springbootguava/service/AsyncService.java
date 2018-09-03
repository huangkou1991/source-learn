package com.learn.springbootguava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

/**
 * @Author :lwy
 * @Date : 2018/9/3 10:26
 * @Description :异步调用组件
 */
@Component
public class AsyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncService.class);

    @Autowired
    private HelloService helloService;


    @Async
    public ListenableFuture<String> asyncNotify(String id) {
        return new AsyncResult<>(helloService.test());
    }

    public void addCallback(ListenableFuture future, String successful, String failure) {
        final String successInfo = successful;
        final String failureInfo = failure;

        /**
         * 创建异步成功的回调函数
         */
        SuccessCallback<String> successCallback = new SuccessCallback<String>() {
            @Override
            public void onSuccess(String str) {
                //异步执行成功后要进行的操作
                LOGGER.info(successInfo + "，return:" + str);
            }
        };

        /**
         * 创建异步失败的回调函数
         */
        FailureCallback failureCallback = new FailureCallback() {
            @Override
            public void onFailure(Throwable ex) {
                //异步执行失败后要进行的操作
                LOGGER.info(failureInfo + "，exception message:" + ex.getMessage());
            }
        };
        future.addCallback(successCallback, failureCallback);

    }

}

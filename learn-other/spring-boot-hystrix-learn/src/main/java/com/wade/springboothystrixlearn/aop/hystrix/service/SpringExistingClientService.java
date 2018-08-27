package com.wade.springboothystrixlearn.aop.hystrix.service;

import com.wade.springboothystrixlearn.aop.hystrix.HystrixCircuitBreaker;
import com.wade.springboothystrixlearn.hystrix.remote.RemoteServiceTestSimulator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author :lwy
 * @Date : 2018/8/27 12:48
 * @Description :
 */
@Service("springClient")
public class SpringExistingClientService {

    @Value("${remoteservice.timeout}")
    private int remoteServiceDelay;


    @HystrixCircuitBreaker
    public String invokeRemoteServiceWithHystrix() throws InterruptedException {
        return new RemoteServiceTestSimulator(remoteServiceDelay).execute();
    }

    //添加HystrixCiruitBreaker注解
    public String invokeRemoteServiceWithOutHystrix() throws InterruptedException {
        return new RemoteServiceTestSimulator(remoteServiceDelay).execute();
    }

}

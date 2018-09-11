package com.learn;

import com.alibaba.dubbo.rpc.RpcContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author :lwy
 * @Date : 2018/9/11 19:06
 * @Description :
 */
public class Consumer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"dubbo-demo-consumer.xml"});
        context.start();
        HelloService service = (HelloService) context.getBean("hello"); // get remote service proxy

        service.sayHello("world");

        Future future=RpcContext.getContext().getFuture();

        String result= (String) future.get();

        System.out.println(result);
    }
}

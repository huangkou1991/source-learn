package com.learn.java8.threadpool;

import java.util.concurrent.*;

/**
 * @Author :lwy
 * @Date : 2018/8/21 16:46
 * @Description :
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Executor executor = Executors.newSingleThreadExecutor();
        //executor.execute(() -> System.out.println("hello,world"));
        //((ExecutorService) executor).shutdown();

        ExecutorService service=Executors.newFixedThreadPool(5);
        Future<String> future=service.submit(()->"Hello World");

        String result=future.get();

        System.out.println(result);


        ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newCachedThreadPool();

    }
}

package com.learn.java8.threadpool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author :lwy
 * @Date : 2018/8/21 16:46
 * @Description :
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("hello,world"));
        ((ExecutorService) executor).shutdown();
    }
}

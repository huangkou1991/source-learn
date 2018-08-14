package com.learn.quasar.coroutine.async;

import co.paralleluniverse.fibers.FiberAsync;

/**
 * @author :lwy
 * @date 2018/8/3 11:43
 */
public class FooAsyncTest {

    public static void main(String[] args) throws Exception {
        String fiberAsync = new FiberAsync<String, Exception>() {
            @Override
            protected void requestAsync() {
                System.out.println("hello");
            }
        }.run();

        System.out.println(fiberAsync);
    }

}

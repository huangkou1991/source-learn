package com.learn.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author :lwy
 * @date 2018/7/13 14:55
 */
public class LogBackDemo {
    Logger logger =  LoggerFactory.getLogger(LogBackDemo.class);


    //多线程
    @Test
    public void testThread() throws InterruptedException {
        int THREAD_NUM = 100;
        final int LOOP_NUM = 100000;

        final CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);
        long start = System.currentTimeMillis();
        for(int x= 0;x < THREAD_NUM;x++){
            new Thread(new Runnable() {
                public void run() {
                    for (int y = 0; y < LOOP_NUM; y++) {
                        logger.info("Info Message!");
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println(System.currentTimeMillis() - start);
    }


    //单线程
    @Test
    public void test() {
        int X_NUM = 100;
        int Y_NUM = 100000;

        long start = System.currentTimeMillis();
        for(int x=0;x<X_NUM;x++) {
            for (int y = 0; y < Y_NUM; y++) {
                logger.info("Info Message!");
            }
        }
        System.out.print(System.currentTimeMillis()-start);
    }
}

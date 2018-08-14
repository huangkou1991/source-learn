package com.learn.schedulefuture;

import java.util.concurrent.TimeUnit;

/**
 * @author :lwy
 * @date 2018/7/12 18:12
 */
public class Task implements Runnable {

    /**
     * Main method of the task. Writes a message, sleeps the current thread for two seconds and
     * writes another message
     */
    @Override
    public void run() {
        System.out.printf("Task: Begin.\n");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Task: End.\n");
    }
}

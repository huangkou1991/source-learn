package com.learn.quasar.coroutine;

/**
 * @author :lwy
 * @date 2018/8/3 10:06
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1_000_00; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(10000000);
    }
}

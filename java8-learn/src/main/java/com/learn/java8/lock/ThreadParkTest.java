package com.learn.java8.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author :lwy
 * @Date : 2018/9/30 9:42
 * @Description :
 */
public class ThreadParkTest {

    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.setName("lockPack-thread");
        myThread.start();

        try {
            Thread.sleep(10);
            myThread.park();
            Thread.sleep(10000);
            myThread.unPark();
            Thread.sleep(10000);
            myThread.park();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class MyThread extends Thread {

        private volatile boolean isPark = false;

        @Override
        public void run() {
            System.out.println(" Enter Thread running.....");
            while (true) {
                if (isPark) {
                    System.out.println(Thread.currentThread().getName() +">> "+ "Thread is Park.....");
                    LockSupport.park();
                }
                //doSomething
                System.out.println(Thread.currentThread().getName()+">> is running");
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void park() {
            this.isPark = true;
        }

        public void unPark() {
            this.isPark = false;
            LockSupport.unpark(this);
            System.out.println("Thread is unpark.....");
        }
    }
}

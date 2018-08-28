package com.learn.transferqueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @Author :lwy
 * @Date : 2018/8/27 19:15
 * @Description :
 * https://segmentfault.com/a/1190000011266361
 * https://www.baeldung.com/java-transfer-queue
 */
public class LinkedTransferQueueDemo {
    static TransferQueue<String> lnkTransQueue = new LinkedTransferQueue<String>();

    public static void main(String[] args) {
        ExecutorService exService = Executors.newFixedThreadPool(2);
        Producer producer = new LinkedTransferQueueDemo().new Producer();
        Consumer consumer = new LinkedTransferQueueDemo().new Consumer();
        exService.execute(producer);
        exService.execute(consumer);
        exService.shutdown();
    }

    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println("Producer is waiting to transfer...");
                    lnkTransQueue.transfer("A" + i);
                    System.err.println("producer transfered element: A" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println("Consumer is waiting to take element...");

                    //注释与不注释结果比较
                    String s = lnkTransQueue.take();
                    System.out.println("Consumer received Element: " + s);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
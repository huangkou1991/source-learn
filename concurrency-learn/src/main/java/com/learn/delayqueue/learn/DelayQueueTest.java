package com.learn.delayqueue.learn;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author :lwy
 * @Date : 2018/8/27 18:24
 * @Description :
 */
public class DelayQueueTest {

    public static void main(String[] args) {
        // 创建延时队列
        DelayQueue<Message> queue = new DelayQueue<Message>();
        // 添加延时消息,m1 延时3s
        Message m1 = new Message(1, "world", 3000);
        // 添加延时消息,m2 延时10s
        Message m2 = new Message(2, "hello", 10000);

        Message m3=new Message(3,"hi",200);
        //将延时消息放到延时队列中
        queue.offer(m2);
        queue.offer(m1);
        queue.offer(m3);
        // 启动消费线程 消费添加到延时队列中的消息，前提是任务到了延期时间
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.execute(new Consumer(queue));
        exec.shutdown();
    }
}

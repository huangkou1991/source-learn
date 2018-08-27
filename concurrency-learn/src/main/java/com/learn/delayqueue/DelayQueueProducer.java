package com.learn.delayqueue;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;

/**
 * @Author :lwy
 * @Date : 2018/8/27 18:00
 * @Description :
 */
//延迟队列生产者
public class DelayQueueProducer implements Runnable {

    private BlockingQueue<DelayObject> queue;
    private Integer numberOfElementsToProduce;
    private Integer delayOfEachProducedMessageMilliseconds;


    public DelayQueueProducer(BlockingQueue<DelayObject> queue, Integer numberOfElementsToProduce,
                              Integer delayOfEachProducedMessageMilliseconds) {
        this.queue = queue;
        this.numberOfElementsToProduce = numberOfElementsToProduce;
        this.delayOfEachProducedMessageMilliseconds = delayOfEachProducedMessageMilliseconds;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfElementsToProduce; i++) {
            DelayObject object = new DelayObject(UUID.randomUUID().toString(), delayOfEachProducedMessageMilliseconds);
            System.out.println("Put Objects: " + object);
            try{
                queue.put(object);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

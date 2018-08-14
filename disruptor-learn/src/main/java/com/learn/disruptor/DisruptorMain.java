package com.learn.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author :lwy
 * @date 2018/7/13 15:51
 */
public class DisruptorMain {

    public static void main(String[] args) throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        LogEventFactory factory = new LogEventFactory();
        //ringBuffer的大小
        int bufferSize = 4;
        Disruptor<LogEvent> disruptor = new Disruptor<>(factory, bufferSize, executor, ProducerType.SINGLE, new BlockingWaitStrategy());
        //设置事件执行者
        disruptor.handleEventsWith(new LogEventHandler());
        disruptor.start();


        RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();

        int buffsize=disruptor.getRingBuffer().getBufferSize();

        //设置事件单生产者：
        for (int x = 0; x < 256; x++) {
            // 获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            try {
                // 返回可用位置的元素
                LogEvent event = ringBuffer.get(sequence);
                // 设置该位置元素的值
                event.setMessage("hello,world");
            } finally {
                //发布事件
                ringBuffer.publish(sequence);
            }
        }
    }
}

package com.learn.quasar.coroutine;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.strands.Strand;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author :lwy
 * @date 2018/8/3 10:06
 */
public class FiberTest {

    public static void main(String[] args) throws InterruptedException {
        int FiberNumber = 2_000_000;
        CountDownLatch latch = new CountDownLatch(1);
        AtomicInteger counter = new AtomicInteger(0);

        for (int i = 0; i < FiberNumber; i++) {
            new Fiber(() -> {
                //System.out.println(counter);
                counter.incrementAndGet();
                if (counter.get() == FiberNumber) {
                    System.out.println("done");
                }
                Strand.sleep(1000000);
            }).start();
        }
        latch.await();
    }
}

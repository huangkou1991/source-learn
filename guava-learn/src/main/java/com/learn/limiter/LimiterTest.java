package com.learn.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Author :lwy
 * @Date : 2018/8/29 18:51
 * @Description :
 */
public class LimiterTest {
    private static AtomicInteger limiter = new AtomicInteger(0);

    public static void main(String[] args) {

        RateLimiter limiter = RateLimiter.create(100, 1, TimeUnit.MILLISECONDS);


        long startTime = ZonedDateTime.now().getSecond();
        IntStream.range(0, 1000).forEach(i -> {
            limiter.acquire();
            doSomeLimitedOperation();
        });
        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;
    }

    private static void doSomeLimitedOperation() {

        System.out.println("limit : " + limiter.incrementAndGet());
    }
}

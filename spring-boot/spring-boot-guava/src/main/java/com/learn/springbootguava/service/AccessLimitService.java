package com.learn.springbootguava.service;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * @Author :lwy
 * @Date : 2018/8/29 19:00
 * @Description :
 */
@Service
public class AccessLimitService {

    //每秒只发出500个令牌
    RateLimiter rateLimiter = RateLimiter.create(500.0);

    /**
     * 尝试获取令牌
     *
     * @return
     */
    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }

    public void addLimiter() {
        rateLimiter.setRate(400.0);
        System.out.println("current: " + rateLimiter.getRate());
    }

    public void getLimiter() {
        rateLimiter.getRate();
    }
}

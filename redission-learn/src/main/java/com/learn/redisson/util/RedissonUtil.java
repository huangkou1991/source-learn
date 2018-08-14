package com.learn.redisson.util;

import com.learn.redisson.config.ConfigUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author :lwy
 * @date 2018/6/7 13:01
 */
public class RedissonUtil {

    private static RedissonClient client;

    static {

        client = ConfigUtil.setConfig();
    }

    /**
     * 加锁
     *
     * @param lock
     * @return
     */
    public static RLock setLock(String lock) {
        RLock rLock = client.getLock(lock);
        rLock.lock();
        return rLock;
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public static void releaseLock(String lock) {
        RLock rLock = client.getLock(lock);
        rLock.unlock();
    }

    /**
     * 释放锁
     *
     * @param lock
     */
    public static void releaseLock(RLock lock) {
        lock.unlock();
    }

    /**
     * 尝试获取锁
     *
     * @param lock
     * @return
     */
    public static boolean tryLock(String lock) {
        RLock rLock = client.getLock(lock);
        return rLock.tryLock();
    }

    /**
     * @param lock
     * @param time
     * @return
     */
    public static boolean tryLock(String lock, int time) {
        RLock rLock = client.getLock(lock);
        try {
            return rLock.tryLock(time, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * @param lock
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     * @return
     */
    public static boolean tryLock(String lock, TimeUnit unit, int waitTime, int leaseTime) {
        RLock rLock = client.getLock(lock);
        try {
            return rLock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

}

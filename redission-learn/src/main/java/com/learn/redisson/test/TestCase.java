package com.learn.redisson.test;

import com.learn.redisson.config.ConfigUtil;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author :lwy
 * @date 2018/6/21 11:56
 */
public class TestCase {

    private static RedissonClient client;

    static {

        client = ConfigUtil.setConfig();
    }
    public static void main(String[] args) {

        boolean lock=false;
        try {
            //尝试获取锁，最多等待3秒，上锁以后10秒自动解锁
            RLock rLock = client.getLock(StringUtils.join(1, 2));
            lock = rLock.tryLock(3, 10, TimeUnit.SECONDS);
            //执行业务逻辑
            System.out.println("hello");
        } catch (InterruptedException e) {
            lock = false;
            System.err.println("catch");
        } finally {
            if (lock) {
                RLock rLock = client.getLock(StringUtils.join(1, 2));
                rLock.unlock();
                System.err.println("finally");
            }
        }
    }
}

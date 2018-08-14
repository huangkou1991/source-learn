package com.learn.redisson.service;

import com.learn.redisson.util.RedissonUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author :lwy
 * @date 2018/6/7 13:17
 */
public class RedissonService {


    public void execute() {
        boolean lock = false;
        try {
            //尝试获取锁，最多等待3秒，上锁以后20秒自动解锁（实际项目中推荐这种，以防出现死锁）、这里根据预估秒杀人数，设定自动释放锁时间
            lock = RedissonUtil.tryLock("executeLock", TimeUnit.SECONDS, 3, 20);
            System.err.println(lock);
            //业务逻辑
        } finally {
            if (lock) {
                System.err.println("exexe");
                RedissonUtil.releaseLock("executeLock");
            }
        }

        System.out.println("hello");


    }


    public static void main(String[] args) {
        RedissonService service=new RedissonService();
        service.execute();
    }
}

package com.learn.jedis.util;

import com.learn.jedis.conn.RedisConn;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.UUID;

/**
 * @author :lwy
 * @date 2018/6/8 18:35
 */
public class JedisUtil {


    /**
     * 尝试获取锁
     *
     * @param conn
     * @param key
     * @param time
     * @return
     * @throws InterruptedException
     */
    public static boolean tryAcquireLock(Jedis conn, String key, long time) throws InterruptedException {
        long endTime = System.currentTimeMillis() + time;
        while (System.currentTimeMillis() < endTime) {
            if (conn.setnx("lock:" + key, "1") == 1) {
                System.err.println("....");
                return true;
            }
            Thread.sleep(1);
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param conn
     * @param key
     * @return
     */
    public static boolean releaseLock(Jedis conn, String key) {
        String lockName = "lock:" + key;
        while (true) {
            if (conn.get(lockName).equals("2")) {
                conn.del(lockName);
                System.err.println("....");
                return true;
            }
            break;
        }
        return false;
    }
}

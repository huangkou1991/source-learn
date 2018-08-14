package com.learn.jedis.conn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author :lwy
 * @date 2018/6/8 18:39
 */
public class RedisConn {

    private static JedisPool jedisPool;

    static {
        jedisPool = new JedisPool("redis.cornfield-ads.slave");
    }

    /**
     * 获取
     *
     * @return
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

}

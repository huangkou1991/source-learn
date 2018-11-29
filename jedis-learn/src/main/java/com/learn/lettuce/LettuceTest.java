package com.learn.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @Author :lwy
 * @Date : 2018/11/28 16:55
 * @Description :
 */
public class LettuceTest {

    public static void main(String[] args) {

        RedisClient client=RedisClient.create(RedisURI.create("redis://localhost:6379"));
        //System.out.println(client);
        StatefulRedisConnection<String, String> connect = client.connect();

        RedisCommands<String, String> command = connect.sync();

    }
}

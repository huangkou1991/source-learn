package com.learn.redisson.client;

import com.learn.redisson.config.ConfigUtil;
import org.redisson.api.RedissonClient;

/**
 * @author :lwy
 * @date 2018/6/4 20:25
 */
public class ClientTest {


    public static void main(String[] args) {

        RedissonClient client=ConfigUtil.setConfig();
        System.err.println(client);
        client.shutdown();
    }
}

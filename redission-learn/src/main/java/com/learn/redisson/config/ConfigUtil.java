package com.learn.redisson.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.CodecProvider;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

/**
 * @author :lwy
 * @date 2018/6/4 20:16
 */
public class ConfigUtil {



    public static RedissonClient setConfig(){
        Config config=new Config();

        SingleServerConfig singleServerConfig=config.useSingleServer()
                .setAddress("redis.cornfield-ads.slave:6379")
                .setDatabase(2)
                .setConnectTimeout(100000)
                .setConnectionPoolSize(64)
                .setConnectionMinimumIdleSize(10);
        return Redisson.create(config);
    }
}

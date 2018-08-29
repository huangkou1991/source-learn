package com.learn.springbootzookeeper.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author :lwy
 * @Date : 2018/8/28 18:01
 * @Description :
 */
@Configuration
public class AppConfig {

    @Autowired
    private ApplicationConfiguration configuration;

    @Bean
    public ZkClient newZkClient(){
        return new ZkClient(configuration.getZkAddr(),5000);
    }


    @Bean
    public LoadingCache<String,String> newInstance(){
        return CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return null;
                    }
                });
    }
}

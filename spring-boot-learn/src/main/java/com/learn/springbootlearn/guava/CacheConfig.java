package com.learn.springbootlearn.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @Author :lwy
 * @Date : 2018/8/21 15:05
 * @Description :
 */
@Configuration
public class CacheConfig {

    @Bean
    public LoadingCache<String, Integer> buildCache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(20, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        return 1;
                    }
                });
    }
}

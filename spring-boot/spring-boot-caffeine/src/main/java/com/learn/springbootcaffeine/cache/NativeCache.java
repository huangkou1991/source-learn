package com.learn.springbootcaffeine.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author :lwy
 * @Date : 2018/9/17 18:29
 * @Description :
 */
@Configuration
public class NativeCache {

    private static LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
            .build(s -> null);


    @PostConstruct
    public void initCache() {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");

        strings.forEach(
                s -> loadingCache.put(s, s)
        );
    }

    public String getCacheValue(String key) {
        return loadingCache.get(key, s -> s + "s");
    }
}

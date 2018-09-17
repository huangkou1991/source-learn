package com.learn.springbootcaffeine.controller;

import com.learn.springbootcaffeine.cache.NativeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :lwy
 * @Date : 2018/9/17 18:37
 * @Description :
 */
@RestController
public class CacheController {

    @Autowired
    private NativeCache cache;

    @GetMapping("/cache/{key}")
    public String get(@PathVariable String key){
        return cache.getCacheValue(key);
    }
}

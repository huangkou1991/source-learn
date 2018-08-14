package com.learn.quasar.coroutine.async;

/**
 * @author :lwy
 * @date 2018/8/3 11:40
 */
public interface FooCompletion {
    void success(String result);
    void failure(Exception exception);
}

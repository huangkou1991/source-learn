package com.learn.quasar.coroutine.async;

import co.paralleluniverse.fibers.FiberAsync;

/**
 * @author :lwy
 * @date 2018/8/3 11:41
 */
public class FooAsync extends FiberAsync<String,Exception> implements FooCompletion {

    @Override
    protected void requestAsync() {
    }

    @Override
    public void success(String result) {
        asyncCompleted(result);
    }

    @Override
    public void failure(Exception exception) {
        asyncFailed(exception);
    }
}

package com.learn.jdk.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author :lwy
 * @date 2018/7/6 17:35
 */
public class DynamicProxy implements InvocationHandler {

    Object target;

    public Object getProxyTarget(Object obj) {
        this.target = obj;
        return Proxy.newProxyInstance(this.target.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = method.invoke(target, args);
        Long span = System.currentTimeMillis() - start;
        System.out.println("共用时：" + span);
        return obj;
    }
}

package com.learn.cglib.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author :lwy
 * @date 2018/7/6 19:03
 */
public class DynamicProxy implements MethodInterceptor {
    Object target;

    public Object getProxyObject(Object target) {
        this.target = target;
        Enhancer enhancer=new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(this.target.getClass());
        return enhancer.create();

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        long start=System.currentTimeMillis();
        Object object=methodProxy.invoke(target,objects);
        long end=System.currentTimeMillis();
        System.err.println(end-start);

        return object;
    }
}

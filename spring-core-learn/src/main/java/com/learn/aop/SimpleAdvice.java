package com.learn.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author :lwy
 * @date 2018/7/10 9:54
 */
public class SimpleAdvice  implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("before");
        Object retVal=methodInvocation.proceed();
        System.out.println("after");
        return retVal;
    }
}

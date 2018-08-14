package org.google.asm.aop;

/**
 * @author :lwy
 * @date 2018/7/5 18:27
 */
public class AopInterceptor {
    public static void beforeInvoke() {
        System.out.println("before");
    }
    public static void afterInvoke() {
        System.out.println("after");
    }
}


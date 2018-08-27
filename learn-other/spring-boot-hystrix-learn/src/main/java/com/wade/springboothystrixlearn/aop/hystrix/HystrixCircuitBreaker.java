package com.wade.springboothystrixlearn.aop.hystrix;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author :lwy
 * @Date : 2018/8/27 12:45
 * @Description :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface HystrixCircuitBreaker {
}

package com.learn.springbootlearn.aop;

import java.lang.annotation.*;

/**
 * @author :lwy
 * @date 2018/7/31 18:09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MethodAnnotation {
}

package com.learn.annotation;

import java.lang.annotation.*;

/**
 * @author :lwy
 * @date 2018/6/14 17:50
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Document {
    String indexName() default "";

    String type() default "";
}

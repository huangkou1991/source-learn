package com.learn.java8.lambda;

/**
 * @author :lwy
 * @date 2018/6/3 22:07
 * @FunctionalInterface https://blog.csdn.net/aitangyong/article/details/54137067
 * 函数式接口详解
 */
@FunctionalInterface
public interface Functional {

    void execute();

    //该方法属于Object
    boolean equals(Object obj);

    default boolean toDo() {
        return true;
    }
}

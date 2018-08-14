package com.learn.java8.lambda;

import java.util.Comparator;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author :lwy
 * @date 2018/6/3 22:13
 */
public class LambdaExpress {

    public static void main(String[] args) {


        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };


        //lambda                         参数        body部分
        Comparator<Integer> comparator1 = (o1, o2) -> o1.compareTo(o2);

        //lambda
        Comparator<Integer> comparator2 = Integer::compareTo;

        Function<String, Object> stringConsumer = (String s) -> s.length();

        Predicate<String> hello = (String s) -> s.equals("hello");
        DoubleSupplier doubleSupplier = () -> 43;

        System.err.println(hello);
    }
}

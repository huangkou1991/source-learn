package com.learn.java8.stream;

import java.util.stream.Stream;

/**
 * @author :lwy
 * @date 2018/5/31 11:49
 */
public class InfiniteStreams {

    public static void main(String[] args) {
        doWhileOldWay();

        doWhileStreamWay();

    }

    private static void doWhileOldWay() {

        int i = 0;
        while (i < 10) {
            i++;
        }
    }

    private static void doWhileStreamWay() {

        Stream<Integer> integers = Stream.iterate(0, i -> i + 1);
        integers.limit(10).forEach(System.out::println);

    }

}

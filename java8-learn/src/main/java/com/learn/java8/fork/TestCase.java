package com.learn.java8.fork;

import java.util.concurrent.ForkJoinPool;

/**
 * @Author :lwy
 * @Date : 2018/8/21 18:01
 * @Description :
 */
public class TestCase {

    public static void main(String[] args) {
        TreeNode tree = new TreeNode(5, new TreeNode(3), new TreeNode(2, new TreeNode(2), new TreeNode(8)));

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int sum = forkJoinPool.invoke(new CountingTask(tree));

        System.out.println(sum);
    }
}

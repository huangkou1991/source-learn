package com.learn.java8.fork;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Author :lwy
 * @Date : 2018/8/21 17:55
 * @Description :
 */
public class CountingTask extends RecursiveTask<Integer> {
    private final TreeNode node;

    public CountingTask(TreeNode node) {
        this.node = node;
    }

    @Override
    protected Integer compute() {
        return node.value + node.children.stream()
                .map(childNode -> new CountingTask(childNode).fork())
                .mapToInt(ForkJoinTask::join).sum();

        //.collect(Collectors.summingInt(ForkJoinTask::join));
    }
}

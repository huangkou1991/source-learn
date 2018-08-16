package com.learn.curotor;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author :lwy
 * @Date : 2018/8/16 19:09
 * @Description :CuratorListener使用，监听事件的改变
 */
public class CuratorListenerTest1 {
    public static void main(String[] args) {
        CuratorFramework client = getClient();
        String path = "/p1";

        try {
            CuratorListener listener = new CuratorListener() {
                @Override
                public void eventReceived(CuratorFramework client, CuratorEvent event) {
                    System.out.println("监听事件触发，event内容为：" + event);
                }
            };
            client.getCuratorListenable().addListener(listener);
            // 异步获取节点数据
            client.getData().inBackground().forPath(path);
            // 变更节点内容
            client.setData().forPath(path, "123".getBytes());

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
            client.close();
        } finally {
            client.close();
        }

    }

    private static CuratorFramework getClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("demo")
                .build();
        client.start();
        return client;
    }
}

/**
 * 其中两次触发监听事件，第一次触发为注册监听事件时触发，第二次为getData异步处理返回结果时触发。而setData的方法并未触发监听事件。
 */

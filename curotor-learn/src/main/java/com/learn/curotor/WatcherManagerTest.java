package com.learn.curotor;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @Author :lwy
 * @Date : 2018/8/16 18:57
 * @Description :
 */
public class WatcherManagerTest {

    public static void main(String[] args) {
        CuratorFramework client = newClient();

        String path = "/p1";
        try {
            byte[] content = client.getData().usingWatcher(new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("监听器watchedEvent：" + event);
                }
            }).forPath(path);

            System.out.println("监听节点内容：" + new String(content));
            // 第一次变更节点数据
            client.setData().forPath(path, "new content".getBytes());

            // 第二次变更节点数据
            client.setData().forPath(path, "second content".getBytes());

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CuratorFramework newClient() {
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework framework = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(policy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("demo")
                .build();
        framework.start();
        return framework;
    }
}

/**
 * 执行此程序之后，首先会对节点/p1注册一个Watcher监听事件，同时返回当前节点的内容信息。随后改变节点内容为“new content”，
 * 此时触发监听事件，并打印出监听事件信息。但当第二次改变节点内容时，监听已经失效，无法再次获得节点变动事件。
 */

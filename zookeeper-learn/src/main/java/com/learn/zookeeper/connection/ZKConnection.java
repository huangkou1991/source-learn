package com.learn.zookeeper.connection;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author :lwy
 * @date 2018/5/31 12:53
 */
public class ZKConnection {

    private ZooKeeper zooKeeper;

    final CountDownLatch connectionLatch=new CountDownLatch(1);

    public ZKConnection() {
    }

    public ZooKeeper connect(String host) throws IOException, InterruptedException {
        zooKeeper=new ZooKeeper(host, 2000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if(watchedEvent.getState()==Event.KeeperState.SyncConnected){
                    connectionLatch.countDown();
                }
            }
        });
        connectionLatch.await();
        return zooKeeper;
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }

}

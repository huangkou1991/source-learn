package com.learn.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author :lwy
 * @Date : 2018/11/22 14:41
 * @Description :
 */
public class Lock {

    public static void main(String[] args) throws Exception {
        RetryPolicy policy = new ExponentialBackoffRetry(100, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2181", policy);
        curatorFramework.start();


        //创建分布式锁, 锁空间的根节点路径为/curator/lock
        InterProcessMutex mutex =new InterProcessMutex(curatorFramework,"/curator/lock");
        mutex.acquire();

        System.err.println("进入mutex");

        mutex.release();

        //关闭客户端
        curatorFramework.close();
    }
}

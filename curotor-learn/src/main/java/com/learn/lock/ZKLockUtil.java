package com.learn.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkImpl;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author :lwy
 * @date 2018/6/25 19:45
 * <p>
 * zk分布式锁
 * https://blog.csdn.net/nimasike/article/details/51567653
 */
public class ZKLockUtil {

    static CuratorFramework client;

    static {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.newClient("node1.zk.dmp.dmp.com:2181", retryPolicy);
        client.start();
    }



    //获取锁
    public static void tryLock(String key){

        try {
            InterProcessMutex mutex=new InterProcessMutex(client,"/lock/"+key);
            mutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //释放锁
    public static void releaseLock(String key) {
        try {
            InterProcessMutex mutex=new InterProcessMutex(client,"/lock/"+key);
            mutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CuratorFramework getClient() {
        return client;
    }


    public static void main(String[] args) {

        ZKLockUtil.tryLock("hello4");
        System.out.println("eee");
        ZKLockUtil.releaseLock("hello4");
    }
}

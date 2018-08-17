package com.learn.pubsub;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author :lwy
 * @Date : 2018/8/17 17:04
 * @Description :
 */
public class SubscribeServer {
    private static DBConfig dbConfig;
    private static CuratorFramework client;
    private static NodeCache nodeCache = null;

    public static void main(String[] args) throws InterruptedException {
        init();
        subscribeInfo();
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 初始化操作
     */
    public static void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .build();
        client.start();

    }

    /**
     *  读取本地配置文件到一个DBConfig对象中。
     */
    public static void readConfig() {
        BufferedReader reader = null;//加载文件流
        System.out.println("读取本地数据库信息。。。。。。");
        try {
            reader = new BufferedReader(new FileReader("E:\\workspace\\idea\\learn\\source-learn\\curotor-learn\\config\\dbconfig.properties"));
            Properties prop = new Properties();//创建属性操作对象
            prop.load(reader);//加载流
            dbConfig = new DBConfig(prop.getProperty("url"),prop.getProperty("driver"),prop.getProperty("username"),prop.getProperty("password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("本地数据库配置信息为：" + dbConfig.toString());
    }


    /**
     * 反序列化得到数据库内容
     */
    public static void unSerialize() {
        System.out.println("读取ZooKeeper服务器数据库信息。。。。。。");
        byte[] data = new byte[0];
        try {
            data = client.getData().forPath(ZKConstants.configPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Kryo kryo = new Kryo();
        Input input = new Input(data);
        dbConfig = kryo.readObject(input, DBConfig.class);
        input.close();
        System.out.println("ZooKeeper中的数据为：" + dbConfig.toString());
    }


    /**
     * 订阅ZooKeeper中的信息，也就是设置监听,如果ZooKeeper中没有对应的信息的话，就读取本地的数据库信息
     */
    public static void subscribeInfo() {
        nodeCache = new NodeCache(client, ZKConstants.configPath);
        try {
            nodeCache.start(true);
            if (nodeCache.getCurrentData() != null) {
                if (!(new String(nodeCache.getCurrentData().getData()).equals(""))) {
                    unSerialize();
                } else {
                    readConfig();
                }
            } else {//否则读取本地文件
                readConfig();
            }
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println("数据库节点信息发生变化，读取新的数据库信息！");
                    unSerialize();//反序列化得到信息
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 取消订阅
     */
    public static void unSubscribeInfo() {
        if (nodeCache != null) {
            try {
                System.out.println("取消订阅！");
                nodeCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

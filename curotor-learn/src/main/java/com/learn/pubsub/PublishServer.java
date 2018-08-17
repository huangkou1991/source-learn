package com.learn.pubsub;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author :lwy
 * @Date : 2018/8/17 10:18
 * @Description :利用zookeeper发布消息
 */
public class PublishServer {

    public static DBConfig dbConfig;
    public static CuratorFramework client;


    public static void main(String[] args) {
        init();
        readConfig();
        publishInfo();
    }


    private static void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .build();
        client.start();

        try {
            if (client.checkExists().forPath(ZKConstants.parentPath) == null) {
                client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(ZKConstants.parentPath);
            }
            if (client.checkExists().forPath(ZKConstants.configPath) == null) {
                client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(ZKConstants.configPath, "".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取配置文件
     */
    private static void readConfig() {
        BufferedReader reader = null;
        try {
            //"E:\\workspace\\idea\\learn\\source-learn\\curotor-learn\\config\\dbconfig.properties"
            //"E:\\workspace\\idea\\learn\\source-learn\\curotor-learn\\src\\main\\resources\\dbconfig.properties"
            reader = new BufferedReader(new FileReader("E:\\workspace\\idea\\learn\\source-learn\\curotor-learn\\config\\dbconfig.properties"));
            Properties prop = new Properties();//创建属性操作对象
            prop.load(reader);//加载流
            dbConfig = new DBConfig(prop.getProperty("url"), prop.getProperty("driver"), prop.getProperty("username"), prop.getProperty("password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将DBConfig使用Kryo序列化之后发布到ZooKeeper节点中
     */
    public static void publishInfo() {
        try {
            Kryo kryo = new Kryo();
            Output output = new Output(1,1024);
            kryo.writeObject(output, dbConfig);
            output.close();
            client.setData().forPath(ZKConstants.configPath, output.getBuffer());//添加到节点中
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

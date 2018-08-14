package com.learn.zookeeper.manager;

import com.learn.zookeeper.connection.ZKConnection;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * @author :lwy
 * @date 2018/5/31 13:11
 */
public class ZkManagerImpl implements ZKManager {
    private ZooKeeper zooKeeper;
    private ZKConnection connection;

    public ZkManagerImpl() {
        init();
    }

    private void init() {
        try {
            connection = new ZKConnection();
            zooKeeper = connection.connect("zk1.dmp.com");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void create(String path, byte[] date) throws KeeperException, InterruptedException {
        zooKeeper.create(path, date, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Override
    public Object getZNodeDate(String path, boolean watchFlag) {
        byte[] b = null;
        try {
            b = zooKeeper.getData(path, null, null);
            String data = new String(b, "UTF-8");
            System.err.println(data);
            return data;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(String path, byte[] data) throws KeeperException, InterruptedException, KeeperException {
        int version = zooKeeper.exists(path, true).getVersion();
        zooKeeper.setData(path, data, version);
    }
}

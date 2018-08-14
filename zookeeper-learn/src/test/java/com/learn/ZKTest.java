package com.learn;

import com.learn.zookeeper.manager.ZKManager;
import com.learn.zookeeper.manager.ZkManagerImpl;
import org.apache.zookeeper.KeeperException;
import org.junit.Test;

/**
 * @author :lwy
 * @date 2018/5/31 13:20
 */
public class ZKTest {


    @Test
    public void create() throws KeeperException, InterruptedException {
        ZKManager zkManager = new ZkManagerImpl();
        zkManager.create("/learnzk", new byte[]{1, 0});
        ((ZkManagerImpl) zkManager).closeConnection();
    }

    @Test
    public void getNode() throws KeeperException, InterruptedException {
        ZKManager zkManager = new ZkManagerImpl();
        Object o=zkManager.getZNodeDate("/learnzk",false);
        ((ZkManagerImpl) zkManager).closeConnection();
        System.err.print(o);
    }

    @Test
    public void update() throws KeeperException, InterruptedException {
        ZKManager zkManager = new ZkManagerImpl();
        zkManager.update("/learnzk",new byte[]{2,3});
        ((ZkManagerImpl) zkManager).closeConnection();
    }
}

package com.learn.zookeeper.manager;

import org.apache.zookeeper.KeeperException;

/**
 * @author :lwy
 * @date 2018/5/31 13:08
 */
public interface ZKManager {

    /**
     * create a zNode and save some date
     * @param path
     * @param date
     * @throws KeeperException
     * @throws InterruptedException
     */
    void create(String path,byte[] date)throws KeeperException, InterruptedException;

    /**
     * get zNode Data
     * @param path
     * @param watchFlag
     * @return
     */
    Object getZNodeDate(String path,boolean watchFlag);
    /**
     * Update the ZNode Data
     *
     * @param path
     * @param data
     * @throws KeeperException
     * @throws InterruptedException
     */
     void update(String path, byte[] data) throws KeeperException, InterruptedException, KeeperException;

}

package com.learn.zookeeper.master;

import org.apache.zookeeper.*;

/**
 * @Author :lwy
 * @Date : 2018/8/16 16:44
 * @Description : 实现ZK master选举机制
 * 原理:https://blog.csdn.net/qq_32523587/article/details/62226611
 */
public class MasterSelectorNode2 implements Watcher {

    private ZooKeeper zk;
    private String hostName;
    private String zNode;

    public MasterSelectorNode2(String hostName, String zNode) throws Exception {
        this.hostName = hostName;
        this.zNode = zNode;

        this.zk = new ZooKeeper(this.hostName, 3000, this);

        try {
            //每个客户端都创建同一个节点，如果创建成功，则该客户端是master
            zk.create(this.zNode, "node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("master节点是：node1");
        } catch (KeeperException.NodeExistsException e) {
            //如果抛出节点存在的异常，则master已经存在，在该节点上添加watcher
            System.out.println("master节点是：" + new String(zk.getData(this.zNode, false, null)));
            zk.exists(this.zNode, true);
        }
    }

    /**
     * 功能描述: 实现master选举
     *
     * @param:
     * @return:
     * @auther:
     * @date:
     */
    @Override
    public void process(WatchedEvent event) {
        try {
            //节点删除
            if (event.getType() == Event.EventType.NodeDeleted) {
                //重新选举master
                try {
                    zk.create(zNode, "node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                    System.out.println("master节点是：node1");
                } catch (KeeperException.NodeExistsException e) {
                    System.out.println("master节点是：" + new String(zk.getData(zNode, false, null)));
                    zk.exists(zNode, true);
                }
            }
        } catch (KeeperException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Throwable {
        new MasterSelectorNode2("localhost:2181", "/com/hello/MasterElection/master");

        System.in.read();
    }
}

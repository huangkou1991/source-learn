package com.learn.pubsub;

/**
 * @Author :lwy
 * @Date : 2018/8/17 16:42
 * @Description :
 */
public interface ZKConstants {
    public static final String zkAddress = "centos3";
    public static final int sessionTimeout = 2000;
    public static String parentPath = "/Pub-Sub";//父节点
    public static String configPath = parentPath + "/DBConfig";//存放配置信息的节点
}

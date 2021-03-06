package com.learn.servicelearndemo.pma;

import cn.suniper.mesh.discovery.PlumApplication;
import cn.suniper.mesh.discovery.annotation.AsProvider;
import cn.suniper.mesh.discovery.annotation.KvStoreBean;
import cn.suniper.mesh.discovery.cli.PlumContext;
import cn.suniper.mesh.discovery.commons.ConfigManager;
import cn.suniper.mesh.discovery.commons.ConnAutoInitializer;
import cn.suniper.mesh.discovery.model.Application;
import cn.suniper.mesh.discovery.model.ProviderInfo;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;


/**
 * @Author :lwy
 * @Date : 2018/9/12 17:24
 * @Description :
 */
@AsProvider
public class ZkCaseTest {

    public static void main(String[] args) throws IOException {
        Object primary = new ZkCaseTest();

        // 设置服务名、开放的端口，所属的server group（用于服务发现）
        ProviderInfo providerInfo = new ProviderInfo("test-provider-1", 8090);
        Application application = new Application(null, providerInfo, "demo");

        // 初始化ConfigManager实例
        ConfigManager configManager = ConfigManager.newBuilder().withAppInfo(application).build();

        try {
            PlumContext launch = PlumApplication.launch(primary, configManager);
            System.err.println(launch);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.in.read();
    }

    @KvStoreBean
    public ZooKeeper getZkClient() throws IOException, InterruptedException {
        ZooKeeper zooKeeper= ConnAutoInitializer.zkConnection(
                "192.168.30.121:2181", 50000000);
        //启动注册/

        return zooKeeper;
    }
}

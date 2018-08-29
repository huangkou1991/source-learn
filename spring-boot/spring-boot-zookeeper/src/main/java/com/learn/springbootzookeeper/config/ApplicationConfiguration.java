package com.learn.springbootzookeeper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author :lwy
 * @Date : 2018/8/28 17:37
 * @Description :
 */
@Configuration
public class ApplicationConfiguration {

    @Value("${app.zk.root}")
    private String zkRoot;

    @Value("${app.zk.addr}")
    private String zkAddr;

    @Value("${app.zk.switch}")
    private boolean zkSwitch;

    @Value("${server.port}")
    private int port;


    public String getZkRoot() {
        return zkRoot;
    }

    public void setZkRoot(String zkRoot) {
        this.zkRoot = zkRoot;
    }

    public String getZkAddr() {
        return zkAddr;
    }

    public void setZkAddr(String zkAddr) {
        this.zkAddr = zkAddr;
    }

    public boolean isZkSwitch() {
        return zkSwitch;
    }

    public void setZkSwitch(boolean zkSwitch) {
        this.zkSwitch = zkSwitch;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

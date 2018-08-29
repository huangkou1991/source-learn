package com.learn.springbootzookeeper.util;

import com.learn.springbootzookeeper.config.ApplicationConfiguration;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author :lwy
 * @Date : 2018/8/28 18:09
 * @Description :
 */
@Component
public class ZKUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(ZKUtil.class);

    @Autowired
    private ZkClient zkClient;

    @Autowired
    private ApplicationConfiguration configuration;


    public void createParentNode() {
        if (zkClient.exists(configuration.getZkRoot())) {
            return;
        }
        zkClient.createPersistent(configuration.getZkRoot());
    }

    public void createNode(String path, String value) {
        zkClient.createEphemeral(path, value);
    }

    /**
     * 监听父类节点事件
     * @param parentNode
     */
    public void subscribeEvent(String parentNode) {
        zkClient.subscribeChildChanges(parentNode, new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                LOGGER.info("the childNode is changed.");
            }
        });
    }
}

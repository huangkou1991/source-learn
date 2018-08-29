package com.learn.springbootzookeeper.registry;

import com.learn.springbootzookeeper.config.ApplicationConfiguration;
import com.learn.springbootzookeeper.spring.SpringFactoryContainer;
import com.learn.springbootzookeeper.util.ZKUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author :lwy
 * @Date : 2018/8/28 19:27
 * @Description :
 */
public class RegistryZK implements Runnable {

    private static final Logger LOGGER=LoggerFactory.getLogger(RegistryZK.class);

    private ZKUtil zkUtil;

    private ApplicationConfiguration configuration;

    private String ip;

    private int port;

    public RegistryZK(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.configuration = SpringFactoryContainer.getBean("applicationConfiguration");
        this.zkUtil = SpringFactoryContainer.getBean(ZKUtil.class);
    }

    @Override
    public void run() {

        //创建父节点
        this.zkUtil.createParentNode();
        if(this.configuration.isZkSwitch()){

            String path=configuration.getZkRoot()+ "/ip-" + ip + ":" + port;
            zkUtil.createNode(path, path);
            LOGGER.info("注册 zookeeper 成功，msg=[{}]", path);
        }

        //启动监听服务
        zkUtil.subscribeEvent(configuration.getZkRoot());
    }
}

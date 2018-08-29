package com.learn.springbootzookeeper;

import com.learn.springbootzookeeper.config.ApplicationConfiguration;
import com.learn.springbootzookeeper.registry.RegistryZK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

@SpringBootApplication
public class SpringBootZookeeperApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootZookeeperApplication.class);

    @Autowired
    private ApplicationConfiguration config;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootZookeeperApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //获得本机的ip
        String ip = InetAddress.getLocalHost().getHostAddress();
        LOGGER.info("address: " + ip);
        //注册zookeeper
        Thread thread = new Thread(new RegistryZK(ip, config.getPort()));
        thread.setContextClassLoader(SpringBootZookeeperApplication.class.getClassLoader());
        thread.setName("registryZk");
        thread.start();

    }
}

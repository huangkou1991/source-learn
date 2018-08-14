package com.learn.guice;

import com.google.inject.Inject;
import com.learn.guice.impl.DefaultCommunicator;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @author :lwy
 * @date 2018/5/31 18:12
 */
public class Communication {
    final Date start = new Date();

    @Inject
    private Logger logger;

    @Inject
    private DefaultCommunicator communicator;

    public Communication(Boolean keepRecords) {
        if (keepRecords) {
            System.out.println("keeping records");
        }
    }

    public boolean sendMessage(String message) {
        logger.info("发送消息。。");
        return communicator.sendMessage(message);
    }

    public DefaultCommunicator getCommunicator() {
        return this.communicator;
    }
}

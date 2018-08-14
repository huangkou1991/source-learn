package com.learn.guice.mode.impl;

import com.google.inject.Inject;
import com.learn.guice.enu.CommunicationModel;
import com.learn.guice.mode.CommunicationMode;

import java.util.logging.Logger;

/**
 * @author :lwy
 * @date 2018/5/31 18:20
 */
public class CommunicationModeSmsImpl implements CommunicationMode {

    @Inject
    private Logger logger;

    @Override
    public CommunicationModel getMode() {
        return CommunicationModel.SMS;
    }

    @Override
    public boolean sendMessage(String message) {
        logger.info("SMS message sent");
        return false
                ;
    }
}

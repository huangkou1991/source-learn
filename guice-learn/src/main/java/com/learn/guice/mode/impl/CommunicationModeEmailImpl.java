package com.learn.guice.mode.impl;

import com.learn.guice.enu.CommunicationModel;
import com.learn.guice.mode.CommunicationMode;

/**
 * @author :lwy
 * @date 2018/5/31 18:20
 */
public class CommunicationModeEmailImpl implements CommunicationMode {
    @Override
    public CommunicationModel getMode() {
        return CommunicationModel.EMAIL;
    }

    @Override
    public boolean sendMessage(String message) {
        System.out.println("email");
        return true;
    }
}

package com.learn.guice.mode;

import com.learn.guice.enu.CommunicationModel;

/**
 * @author :lwy
 * @date 2018/5/31 17:49
 */
public interface CommunicationMode {

    CommunicationModel getMode();

    boolean sendMessage(String message);
}

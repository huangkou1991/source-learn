package com.learn.guice.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.learn.guice.mode.CommunicationMode;
import com.learn.guice.Communicator;

/**
 * @author :lwy
 * @date 2018/5/31 17:48
 */
public class DefaultCommunicator implements Communicator {

    private CommunicationMode defaultCommsMode;

    @Inject
    @Named("email")
    CommunicationMode emailMode;

    @Inject
    @Named("sms")
    CommunicationMode smsMode;

    @Inject
    @Named("im")
    CommunicationMode imMode;

    protected DefaultCommunicator(CommunicationMode defaultComms) {
        this.defaultCommsMode = defaultComms;
    }

    public DefaultCommunicator() {

    }

    public boolean sendMessage(String message) {
        boolean sent = false;
        if (defaultCommsMode != null) {
            sent = sendMessageByDefault(message);
        } else {
            if(message.equals("1")){
                sent=emailMode.sendMessage(message);
            }else if(message.equals("2")){
                sent=imMode.sendMessage(message);;
            }else {
                sent = smsMode.sendMessage(message);
            }
        }
        return sent;
    }

    private boolean sendMessageByDefault(String message) {
        boolean sent = false;
        if (message != null && !message.trim().equals("")) {
            return defaultCommsMode.sendMessage(message);
        }
        return sent;
    }
}

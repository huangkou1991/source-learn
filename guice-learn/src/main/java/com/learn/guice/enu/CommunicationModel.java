package com.learn.guice.enu;

/**
 * @author :lwy
 * @date 2018/5/31 17:50
 */
public enum CommunicationModel {

    EMAIL("Email"), SMS("SMS"), IM("IM"), PHONE("Phone");

    private String message;

    CommunicationModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

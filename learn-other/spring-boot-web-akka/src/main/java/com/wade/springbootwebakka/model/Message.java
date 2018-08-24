package com.wade.springbootwebakka.model;

/**
 * @Author :lwy
 * @Date : 2018/8/24 17:37
 * @Description :
 */
public class Message {

    final private String payload;
    final private long id;

    public Message(String payload, long id) {
        this.payload = payload;
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Message{" + "payload='" + payload + '\'' + ", id=" + id + '}';
    }
}

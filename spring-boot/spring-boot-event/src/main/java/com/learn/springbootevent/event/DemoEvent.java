package com.learn.springbootevent.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author :lwy
 * @Date : 2018/9/3 15:22
 * @Description :自定义事件
 */
public class DemoEvent extends ApplicationEvent {

    private String name;

    public DemoEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

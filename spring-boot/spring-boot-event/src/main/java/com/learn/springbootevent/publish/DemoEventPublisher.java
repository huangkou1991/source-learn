package com.learn.springbootevent.publish;

import com.learn.springbootevent.event.DemoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author :lwy
 * @Date : 2018/9/3 15:27
 * @Description :事件发布类
 */
@Component
public class DemoEventPublisher {

    @Autowired
    private ApplicationContext applicationContext;

    public void publishMsg(String msg) {
        applicationContext.publishEvent(new DemoEvent(this, msg));
    }
}

package com.learn.springbootevent.lister;

import com.learn.springbootevent.event.DemoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author :lwy
 * @Date : 2018/9/3 15:24
 * @Description :
 */
@Component
public class DemoEventListener/* implements ApplicationListener<DemoEvent>*/ {

    @Async
    //@Override
    @EventListener
    public void onApplicationEvent(DemoEvent event) {
        System.out.println("注册成功，消息为：" + event.getName());
    }
}

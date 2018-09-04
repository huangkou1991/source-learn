package com.learn.springbootevent.reflection;

import com.learn.springbootevent.lister.DemoEventListener;
import com.learn.springbootevent.publish.DemoEventPublisher;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

/**
 * @Author :lwy
 * @Date : 2018/9/4 12:46
 * @Description :
 */
@Component
public class ReflectionService {


    @PostConstruct
    public void init() {
        Field field = ReflectionUtils.findField(DemoEventPublisher.class, "name");
        assert field != null;
        field.setAccessible(true);

        //ApplicationContext context = null;
        String name=null;
        try {
            name = (String) field.get(DemoEventPublisher.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //DemoEventPublisher publisher = (DemoEventPublisher) context.getBean("demoEventPublisher");
        System.out.println(name);
    }
}

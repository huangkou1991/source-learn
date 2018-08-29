package com.learn.springbootzookeeper.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author :lwy
 * @Date : 2018/8/29 10:39
 * @Description :
 */
@Component
public class SpringFactoryContainer implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

    public static <T> T getBean(Class clz) {
        return (T) context.getBean(clz);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}

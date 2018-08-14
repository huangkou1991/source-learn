package com.learn.springbootlearn.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author :lwy
 * @date 2018/7/9 15:29
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context=applicationContext;
    }

    public static ApplicationContext getContext(){
        return context;
    }
}

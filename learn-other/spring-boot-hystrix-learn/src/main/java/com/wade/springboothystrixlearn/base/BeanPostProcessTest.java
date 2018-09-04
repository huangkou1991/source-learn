package com.wade.springboothystrixlearn.base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Author :lwy
 * @Date : 2018/9/4 14:40
 * @Description :
 */
@Component
public class BeanPostProcessTest implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.err.println("before init : " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.err.println("after init : " + beanName);
        return bean;
    }
}

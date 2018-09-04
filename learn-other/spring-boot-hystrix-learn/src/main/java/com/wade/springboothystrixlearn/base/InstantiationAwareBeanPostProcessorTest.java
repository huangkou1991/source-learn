package com.wade.springboothystrixlearn.base;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

/**
 * @Author :lwy
 * @Date : 2018/9/4 14:45
 * @Description :
 */
@Component
public class InstantiationAwareBeanPostProcessorTest implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.err.println("the start bean beanName :"+beanName);
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.err.println("the after bean beanName :"+beanName);
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {

        System.err.println("pvs :"+pvs);
        System.err.println("pds :"+ Arrays.asList(pds));
        System.err.println("beanName :"+beanName);
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.err.println("beanName1 :"+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("beanName2 :"+beanName);
        return bean;
    }
}

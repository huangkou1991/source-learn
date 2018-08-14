package com.learn.springbootlearn.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author :lwy
 * @date 2018/7/9 12:04
 */
@Component
public class PersonFactoryBean<T> implements FactoryBean<T> {
    @Override
    public T getObject() throws Exception {
        return (T) new Person();
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}

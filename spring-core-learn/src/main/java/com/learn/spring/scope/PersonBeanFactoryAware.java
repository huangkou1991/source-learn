package com.learn.spring.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * @author :lwy
 * @date 2018/6/18 12:08
 */
public class PersonBeanFactoryAware implements BeanFactoryAware {

    private BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        beanFactory=beanFactory;
    }


    public Man getMan(){
        return (Man) beanFactory.getBean("man");
    }

    public void persistNew() {
        System.out.println("persist bean : " + getMan());
    }
}

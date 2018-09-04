package com.wade.springboothystrixlearn.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author :lwy
 * @Date : 2018/9/4 14:37
 * @Description :
 */
@Component
public class FactoryBeanTestCase {

    @Autowired
    private ApplicationContext context;


    @PostConstruct
    public void test(){
       Object factory=context.getBean("factoryBeanTest");
       System.out.println(factory);
    }
}

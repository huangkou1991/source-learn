package com.wade.springboothystrixlearn.base;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author :lwy
 * @Date : 2018/9/4 14:31
 * @Description :
 */
@Component
public class FactoryBeanTest implements FactoryBean<String> {
    @Override
    public String getObject() throws Exception {
        return "hello";
    }

    @Override
    public Class<?> getObjectType() {
        /*Thread thread = new Thread();
        thread.setContextClassLoader(FactoryBeanTest.class.getClassLoader());
        return thread.getContextClassLoader().getClass();*/
        return FactoryBeanTest.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

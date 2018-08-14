package com.learn.spring.register;

import com.learn.bean.People;
import com.learn.bean.PeopleTest;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author :lwy
 * @date 2018/6/11 21:49
 * 1.直接编码方式注入bean
 */
public class RegisterTest {
    public static void main(String[] args) {

        DefaultListableBeanFactory beanRegister=new DefaultListableBeanFactory();
        BeanFactory container=bindViaCode(beanRegister);
        People people= (People) container.getBean("people");
        System.out.println(people);
    }

    private static BeanFactory bindViaCode(DefaultListableBeanFactory beanRegister) {
        AbstractBeanDefinition newProvider=new RootBeanDefinition(People.class,true);
        AbstractBeanDefinition newLister=new RootBeanDefinition(PeopleTest.class,true);
        beanRegister.registerBeanDefinition("people",newProvider);
        beanRegister.registerBeanDefinition("test",newLister);
        return  beanRegister;

    }
}

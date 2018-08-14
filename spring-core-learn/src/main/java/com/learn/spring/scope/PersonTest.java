package com.learn.spring.scope;

import com.learn.bean.People;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author :lwy
 * @date 2018/6/18 11:56
 */
public class PersonTest {


    public static void main(String[] args) {

        BeanFactory beanFactory=new XmlBeanFactory(new ClassPathResource("scope.xml"));

        Object ob =beanFactory.getBean("person");
        PersonBeanFactoryAware person= (PersonBeanFactoryAware) ob;
        person.persistNew();
        person.persistNew();
        System.err.println(ob);
    }
}

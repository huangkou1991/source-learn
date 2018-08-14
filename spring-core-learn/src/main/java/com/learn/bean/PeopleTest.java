package com.learn.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @author :lwy
 * @date 2018/6/11 21:33
 */
public class PeopleTest {


    public static void main(String[] args) {

        /*BeanFactory beanFactory=new XmlBeanFactory(new ClassPathResource("application.xml"));

        People people= (People) beanFactory.getBean("people");
        System.err.println(people);*/

        ApplicationContext container=new ClassPathXmlApplicationContext("application.xml");
        People people2= (People) container.getBean("people");
        System.err.println(people2);

        /*ApplicationContext container3=new FileSystemXmlApplicationContext("E:\\workspace\\idea\\learn\\source-learn\\spring-core-learn\\src\\main\\resources\\application.xml");
        People people3= (People) container3.getBean("people");
        System.err.println(people3);*/
    }
}

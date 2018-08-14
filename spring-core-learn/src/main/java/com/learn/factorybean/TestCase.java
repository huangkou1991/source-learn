package com.learn.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :lwy
 * @date 2018/6/18 11:36
 */
public class TestCase {


    public static void main(String[] args) throws Exception {

        ApplicationContext container=new ClassPathXmlApplicationContext("factorybean.xml");
        Object nextDayDate =container.getBean("nextDayBean");
        Object nextDayDate1 =container.getBean("&nextDayBean");
        System.err.println(nextDayDate);
        System.err.println(nextDayDate1);
        Object factoryValue=((FactoryBean)nextDayDate1).getObject();

        System.out.println(factoryValue);
    }
}

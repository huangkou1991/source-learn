package com.learn.spring.cotainer.starter;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author :lwy
 * @date 2018/6/18 16:54
 */
public class PostProcessor {


    public static void main(String[] args) {

        ConfigurableBeanFactory beanFactory=new XmlBeanFactory(new ClassPathResource("application.xml"));
        //申明要使用的BeanFactoryPostProcessor
        PropertyPlaceholderConfigurer propertyPostProcessor=new PropertyPlaceholderConfigurer();
        propertyPostProcessor.setLocation(new ClassPathResource("application.properties"));
        //执行后续操作
        propertyPostProcessor.postProcessBeanFactory((ConfigurableListableBeanFactory) beanFactory);
    }
}

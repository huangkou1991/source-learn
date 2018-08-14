package com.learn.spring.cotainer.starter.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author :lwy
 * @date 2018/6/18 17:58
 */
public class PasswordDecodePostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof PasswordDecodable) {
            String encodePassword = ((PasswordDecodable) bean).getEncodedPassword();
            String decodePassword = decodePassword(encodePassword);
            ((PasswordDecodable) bean).setDecodedPassword(decodePassword);
        }
        return  bean;
    }

    private String decodePassword(String encodePassword) {
        return encodePassword + "110";
    }
}

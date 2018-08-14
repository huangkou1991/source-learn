package com.learn.spring.cotainer.starter.beanpostprocessor;

/**
 * @author :lwy
 * @date 2018/6/18 17:54
 */
public interface PasswordDecodable {

    String getEncodedPassword();
    void setDecodedPassword(String  password);
}

package com.learn.spring.cotainer.starter.beanpostprocessor;

/**
 * @author :lwy
 * @date 2018/6/18 17:56
 */
public class PasswordDecodableImpl implements PasswordDecodable {
    private String password;

    @Override
    public String getEncodedPassword() {
        return this.password;
    }

    @Override
    public void setDecodedPassword(String password) {
        this.password=password;
    }
}

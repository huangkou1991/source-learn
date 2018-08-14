package com.learn.spring.scope;

/**
 * @author :lwy
 * @date 2018/6/18 11:51
 */
public class Person {

    private Man man;

    public void persistNew(Man man) {
        persistNew();
    }

    public void persistNew() {
        System.out.println("persist bean : " + getMan());
    }

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }
}

package com.learn.guava.domain;

import com.google.common.base.MoreObjects;

/**
 * @author :lwy
 * @date 2018/6/11 18:14
 */
public class User {

    private long id;
    private String name;

    private int age;

    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }
}

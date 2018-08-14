package com.learn.dozerspringboot.es.domain;

/**
 * @author :lwy
 * @date 2018/6/2 8:23
 */
public class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
